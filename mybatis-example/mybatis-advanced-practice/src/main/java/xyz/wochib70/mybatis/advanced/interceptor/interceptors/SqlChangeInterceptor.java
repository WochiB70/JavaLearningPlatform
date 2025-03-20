package xyz.wochib70.mybatis.advanced.interceptor.interceptors;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Objects;


@Intercepts(
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
)
public class SqlChangeInterceptor implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SqlChangeInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler handler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject.forObject(handler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        if (Objects.isNull(metaObject))
            return invocation.proceed();

        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        SqlCommandType commandType = mappedStatement.getSqlCommandType();
        if (!Objects.equals(commandType, SqlCommandType.SELECT)) {
            return invocation.proceed();
        }

        LOGGER.info("=======================开始修改SQL====================================");
        BoundSql boundSql = handler.getBoundSql();
        MetaObject metaBoundSql = SystemMetaObject.forObject(boundSql);
        LOGGER.info("原始SQL为:{}", metaBoundSql.getValue("sql"));
        String newSqlStr = "SELECT * FROM advanced_interceptor WHERE name LIKE concat('%', ? , '%') LIMIT 1";
        metaBoundSql.setValue("sql", newSqlStr);
        LOGGER.info("原始SQL为:{}", newSqlStr);
        LOGGER.info("=======================修改完成====================================");
        return invocation.proceed();
    }
}
