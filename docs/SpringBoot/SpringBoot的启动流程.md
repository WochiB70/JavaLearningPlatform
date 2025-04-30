# SpringBoot 的启动流程

## SpringApplication 的构造方法

在一般使用 SpringBoot 的场景中会使用如下代码启动 SpringBoot ：

```java
@SpringBootApplication
public class SpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MSpringBootApplication.class, args);
    }

}
```

在这之中通过 `SpringApplication#run()` 静态方法，并将自身的 `class` 对象和启动项参数传递进去，以此来启动 SpringBoot 。

最终在 `SpringApplication#run()` 方法中会通过以下方法，走到 `SpringApplication` 的构造器。

```java
public static ConfigurableApplicationContext run(Class<?> primarySource, String... args) {
    return run(new Class<?>[] { primarySource }, args);
}

public static ConfigurableApplicationContext run(Class<?>[] primarySources, String[] args) {
    return new SpringApplication(primarySources).run(args);
}
```

最终器构建了一个 `SpringApplication` 的实例，并调用了成员方法 `SpringApplication#run()` 来启动 SpringBoot。

`SpringApplication` 的构造方法如下：

```java
public SpringApplication(Class<?>... primarySources) {
    this(null, primarySources);
}

@SuppressWarnings({ "unchecked", "rawtypes" })
public SpringApplication(ResourceLoader resourceLoader, Class<?>... primarySources) {
    this.resourceLoader = resourceLoader;
    Assert.notNull(primarySources, "PrimarySources must not be null");
    this.primarySources = new LinkedHashSet<>(Arrays.asList(primarySources));
    this.properties.setWebApplicationType(WebApplicationType.deduceFromClasspath());
    this.bootstrapRegistryInitializers = new ArrayList<>(
            getSpringFactoriesInstances(BootstrapRegistryInitializer.class));
    setInitializers((Collection) getSpringFactoriesInstances(ApplicationContextInitializer.class));
    setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class));
    this.mainApplicationClass = deduceMainApplicationClass();
}
```

在 `SpringApplication` 的构造器中主要完成了以下几件事：

1. 设置当前运行的环境。（通过class是否存在来进行判断，环境的类型有：None、Servlet、Reactive）
2. 获取所有定义在 `META-INF/spring.factories` 文件中的 `BootstrapRegistryInitializer` 接口的所有实现类。
3. 获取所有定义在 `META-INF/spring.factories` 文件中的 `ApplicationContextInitializer` 接口的所有实现类。
4. 获取所有定义在 `META-INF/spring.factories` 文件中的 `ApplicationListener` 接口的所有实现类。
5. 拿到当前调用堆栈中的程序入口类，并拿到定义程序入口的 `class` 对象

>
> `BootstrapRegistryInitializer` 是一个配置器，用于配置SpringBoot的引导注册器。
> `ApplicationContextInitializer` 是一个初始化器，用于初始化 `ApplicationContext` 容器。
> `ApplicationListener` 是容器的监听器，用于监听特定的容器事件。
>

## SpringBoot 的 `run` 方法

`SpringApplication#run()` 方法就是启动 SpringBoot 的关键方法了。其定义如下：

```java
public ConfigurableApplicationContext run(String... args) {
    Startup startup = Startup.create();
    if (this.properties.isRegisterShutdownHook()) {
        SpringApplication.shutdownHook.enableShutdownHookAddition();
    }
    DefaultBootstrapContext bootstrapContext = createBootstrapContext();
    ConfigurableApplicationContext context = null;
    configureHeadlessProperty();
    SpringApplicationRunListeners listeners = getRunListeners(args);
    listeners.starting(bootstrapContext, this.mainApplicationClass);
    try {
        ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
        ConfigurableEnvironment environment = 
                            prepareEnvironment(listeners, bootstrapContext, applicationArguments);
        Banner printedBanner = printBanner(environment);
        context = createApplicationContext();
        context.setApplicationStartup(this.applicationStartup);
        prepareContext(bootstrapContext, context, environment, 
                                                listeners, applicationArguments, printedBanner);
        refreshContext(context);
        afterRefresh(context, applicationArguments);
        startup.started();
        if (this.properties.isLogStartupInfo()) {
            new StartupInfoLogger(this.mainApplicationClass, environment)
                        .logStarted(getApplicationLog(), startup);
        }
        listeners.started(context, startup.timeTakenToStarted());
        callRunners(context, applicationArguments);
    }
    catch (Throwable ex) {
        throw handleRunFailure(context, ex, listeners);
    }
    try {
        if (context.isRunning()) {
            listeners.ready(context, startup.ready());
        }
    }
    catch (Throwable ex) {
        throw handleRunFailure(context, ex, null);
    }
    return context;
}
```

`SpringApplication#run()` 方法的主要有以下作用：

1. 创建 `Startup` 用于记录系统的启动耗时。
2. 配置是否应该开启系统销毁钩子。
3. 创建引导上下文 `DefaultBootstrapContext`，并使用在 `META-INF/spring.factories` 中查找到的所有 `BootstrapRegistryInitializer` 配置器对引导上下文进行配置。
4. 设置或保留系统的无头模式（Headless Mode）配置。无头模式允许Java应用程序在没有显示器、键盘或鼠标的环境下运行。
5. 获取创建容器的监听器 `SpringApplicationRunListener`，其构造器应该存在 `String[]` 或 `SpringApplication.class` 的入参。
6. 执行所有 `SpringApplicationRunListener` 的 `starting` 方法。
7. 构建 `DefaultApplicationArguments` 用于解析启动命令参数。
8. 准备启动环境，并调用`SpringApplicationRunListener` 的 `environmentPrepared` 方法。
9. 打印程序的 Banner 。
10. 创建 SpringBoot 的 ApplicationContext 容器。并将 `Startup` 实例设置到 ApplicationContext 容器中。
11. 准备 ApplicationContext 容器。准备完成之后销毁引导上下文 `DefaultBootstrapContext`，并调用 `SpringApplicationRunListener` 的 `contextPrepared` 方法。在完成一些对 ApplicationContext 的操作之后调用 `SpringApplicationRunListener` 的 `contextLoaded` 方法。
12. 刷新 ApplicationContext 容器。（Spring容器的核心）
13. 完成 ApplicationContext 容器的一些操作。（目前不进行任何操作）
14. 调用 `SpringApplicationRunListener` 的 `started` 方法。
15. 从 ApplicationContext 中获取所有的 `Runner` 并全部调用。
16. 调用 `SpringApplicationRunListener` 的 `ready` 方法。

至此 SpringBoot 的启动流程就算启动完成了。

## prepareEnvironment 准备启动环境

`SpringApplication#prepareEnvironment()` 代码如下：

```java
private ConfigurableEnvironment prepareEnvironment(SpringApplicationRunListeners listeners,
        DefaultBootstrapContext bootstrapContext, ApplicationArguments applicationArguments) {
    // Create and configure the environment
    ConfigurableEnvironment environment = getOrCreateEnvironment();
    configureEnvironment(environment, applicationArguments.getSourceArgs());
    ConfigurationPropertySources.attach(environment);
    listeners.environmentPrepared(bootstrapContext, environment);
    ApplicationInfoPropertySource.moveToEnd(environment);
    DefaultPropertiesPropertySource.moveToEnd(environment);
    Assert.state(!environment.containsProperty("spring.main.environment-prefix"),
            "Environment prefix cannot be set via properties.");
    bindToSpringApplication(environment);
    if (!this.isCustomEnvironment) {
        EnvironmentConverter environmentConverter = new EnvironmentConverter(getClassLoader());
        environment = environmentConverter
                            .convertEnvironmentIfNecessary(environment, deduceEnvironmentClass());
    }
    ConfigurationPropertySources.attach(environment);
    return environment;
}
```

在准备 SpringBoot 的 Environment 的时候，会先根据当前 SpringBoot 的启动类型来进行判断当前的应该创建什么样的 Environment。

### 创建 Environment

创建 Environment 的代码如下：

```java
private ConfigurableEnvironment getOrCreateEnvironment() {
    if (this.environment != null) {
        return this.environment;
    }
    WebApplicationType webApplicationType = this.properties.getWebApplicationType();
    ConfigurableEnvironment environment = this.applicationContextFactory
                                                    .createEnvironment(webApplicationType);
    if (environment == null && this.applicationContextFactory != ApplicationContextFactory.DEFAULT) {
        environment = ApplicationContextFactory.DEFAULT.createEnvironment(webApplicationType);
    }
    return (environment != null) ? environment : new ApplicationEnvironment();
}
```

在默认环境（非Web、Reactive）环境中，会直接执行 `new ApplicationEnvironment()`。而在其他对应的环境中则会通过对应的 `ApplicationContextFactory` 具体实现来创建 Environment。

在创建 Environment 的之后还需呀对其进行配置。

### 配置 Environment

```java
protected void configureEnvironment(ConfigurableEnvironment environment, String[] args) {
    if (this.addConversionService) {
        environment.setConversionService(new ApplicationConversionService());
    }
    configurePropertySources(environment, args);
    configureProfiles(environment, args);
}
```

1. 配置 Environment 主要是配置 `ApplicationConversionService` 用于将各种格式转换为 Java 的内部对象。
2. 配置 `PropertySources` 用于将 `CommandLine` 参数和 `defaultProperties` 配置到当前环境中去。
3. 配置 `Profiles` 即指定哪一个 application-{option}.yml 文件作为配置项。

### 配置 PropertySource 的顺序

在创建并且配置完成 Environment 之后，由于还触发了 `SpringApplicationRunListener#environmentPrepared` 可能打乱 PropertySourcce 的顺序，所以之后还需要按照SpringBoot的配置重要性对PropertySource进行排序。

## createApplicationContext 创建 Spring 容器

在配置完成 PropertSource 之后就创建了 Spring 核心容器。创建代码如下：

```java
protected ConfigurableApplicationContext createApplicationContext() {
    return this.applicationContextFactory.create(this.properties.getWebApplicationType());
}

@Override
public ConfigurableApplicationContext create(WebApplicationType webApplicationType) {
    try {
        return getFromSpringFactories(webApplicationType, ApplicationContextFactory::create,
                this::createDefaultApplicationContext);
    }
    catch (Exception ex) {
        throw new IllegalStateException("Unable create a default ApplicationContext instance, "
                + "you may need a custom ApplicationContextFactory", ex);
    }
}

private <T> T getFromSpringFactories(WebApplicationType webApplicationType,
        BiFunction<ApplicationContextFactory, WebApplicationType, T> action, Supplier<T> defaultResult) {
    for (ApplicationContextFactory candidate : 
                    SpringFactoriesLoader.loadFactories(ApplicationContextFactory.class,
                                                        getClass().getClassLoader())) {
        T result = action.apply(candidate, webApplicationType);
        if (result != null) {
            return result;
        }
    }
    return (defaultResult != null) ? defaultResult.get() : null;
}
```

可以看到最终 SpringBoot 通过 `META-INF/spring.factories` 文件中配置的 `ApplicationContextFactory` 具体实现来创建适配当前 SpringBoot 类型的容器。

>
> 目前存在着以下三种 ApplicationContextFatory 实现类：
> `DefaultApplicationContextFactory` 对应 None。
> `ReactiveWebServerApplicationContextFactory` 对应 Reactive。
> `ServletWebServerApplicationContextFactory` 对应 Servlet。

## prepareContext 预处理 Spring 容器

在创建完成之后需要进行预处理，其预处理代码如下：

```java
private void prepareContext(DefaultBootstrapContext bootstrapContext, 
        ConfigurableApplicationContext context,
        ConfigurableEnvironment environment, SpringApplicationRunListeners listeners,
        ApplicationArguments applicationArguments, Banner printedBanner) {
    context.setEnvironment(environment);
    postProcessApplicationContext(context);
    addAotGeneratedInitializerIfNecessary(this.initializers);
    applyInitializers(context);
    listeners.contextPrepared(context);
    bootstrapContext.close(context);
    if (this.properties.isLogStartupInfo()) {
        logStartupInfo(context.getParent() == null);
        logStartupInfo(context);
        logStartupProfileInfo(context);
    }
    // Add boot specific singleton beans
    ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
    beanFactory.registerSingleton("springApplicationArguments", applicationArguments);
    if (printedBanner != null) {
        beanFactory.registerSingleton("springBootBanner", printedBanner);
    }
    if (beanFactory instanceof AbstractAutowireCapableBeanFactory autowireCapableBeanFactory) {
        autowireCapableBeanFactory.setAllowCircularReferences(
                                        this.properties.isAllowCircularReferences());
        if (beanFactory instanceof DefaultListableBeanFactory listableBeanFactory) {
            listableBeanFactory.setAllowBeanDefinitionOverriding(
                                        this.properties.isAllowBeanDefinitionOverriding());
        }
    }
    if (this.properties.isLazyInitialization()) {
        context.addBeanFactoryPostProcessor(new LazyInitializationBeanFactoryPostProcessor());
    }
    if (this.properties.isKeepAlive()) {
        context.addApplicationListener(new KeepAlive());
    }
    context.addBeanFactoryPostProcessor(new PropertySourceOrderingBeanFactoryPostProcessor(context));
    if (!AotDetector.useGeneratedArtifacts()) {
        // Load the sources
        Set<Object> sources = getAllSources();
        Assert.notEmpty(sources, "Sources must not be empty");
        load(context, sources.toArray(new Object[0]));
    }
    listeners.contextLoaded(context);
}
```

对 Spring 容器的预处理代码的作用如下：

1. 将 Environment 配置到 Spring 容器中。
2. 配置当前的 `BeanNameGenerator`、`ResourceLoader`、`ClassLoader`、`ConversionService` 到 Spring 容器中。
3. 判断是否可以添加 `AotGeneratedInitializer` 的容器配置器到 Spring 容器中。
4. 使用当前环境中获取到的所有的容器配置器 `ApplicationContextInitializer` 的实现类，来配置 Spring 容器。
5. 触发注册的所有 `SpringApplicationRunListeners#contextPrepared`。
6. 关闭引导上下文，并发出 `BootstrapContextClosedEvent` 如果有监听器则执行所有监听器。
7. 将 `ApplicationArguments` 命令行参数注入到 Spring 容器中。
8. 配置 Spring 容器是否允许：存在循环依赖、重写 BeanDefinition 。
9. 如果配置了懒加载则注入 `LazyInitializationBeanFactoryPostProcessor`
10. 如果配置了keep-Alive 则注入一个 `KeepAlive` 的监听器，用于监听 `ContextRefreshedEvent` 事件时创建一个非守护线程用于包装容器直到容器发出 `ContextClosedEvent` 。
11. 添加 `PropertySourceOrderingBeanFactoryPostProcessor` 用于将 `DefaultPropertiesPropertySource` 排到属性源的末尾。
12. 根据当前是否开启 AOT 配置，来进行一些操作（没用过，暂时不知道作用）
13. 触发注册的所有 `SpringApplicationRunListeners#contextLoaded`。

>
> 守护线程和非守护线程的区别在于：JVM会等待所有非守护线程结束才结束程序，而不会等待守护线程结束。
>

### 刷新 Spring 容器

详细见 Spring 容器的创建
