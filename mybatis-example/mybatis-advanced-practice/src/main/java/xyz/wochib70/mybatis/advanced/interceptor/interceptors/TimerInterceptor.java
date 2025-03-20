package xyz.wochib70.mybatis.advanced.interceptor.interceptors;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;


@Intercepts(
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
)
public class TimerInterceptor implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimerInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        try {
            LOGGER.info("=======================开始查询====================================");
            long startTime = System.currentTimeMillis();
            Object result = invocation.proceed();
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            LOGGER.info("=======================查询结束====================================");
            LOGGER.info("Method: {}, Duration: {} ms", invocation.getMethod().getName(), duration);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
