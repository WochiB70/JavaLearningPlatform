package xyz.woochib70.spring.boot.example.filter;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * {@link AnnotationTypeFilter#AnnotationTypeFilter(Class, boolean, boolean)} 的第二个参数表示是否考虑注解中存在着目标注解
 */
public class TypeFilterMain {


    public static void main(String[] args) throws IOException {
        AnnotationTypeFilter selfMatch = new AnnotationTypeFilter(Component.class, false, false);
        AnnotationTypeFilter parentMatch = new AnnotationTypeFilter(Component.class, true, false);
        AnnotationTypeFilter interfaceMatch = new AnnotationTypeFilter(Component.class, false, true);

        String clazz = SelfComponent.class.getName().replace(".", "/")
                .concat(".class");
        Resource selfResource = new ClassPathResource(clazz);
        Resource parentResource = new ClassPathResource(clazz);
        Resource interfaceResource = new ClassPathResource(clazz);

        SimpleMetadataReaderFactory factory = new SimpleMetadataReaderFactory();
        System.out.println(selfMatch.match(factory.getMetadataReader(selfResource), factory));
        System.out.println(parentMatch.match(factory.getMetadataReader(parentResource), factory));
        System.out.println(interfaceMatch.match(factory.getMetadataReader(interfaceResource), factory));
    }
}
