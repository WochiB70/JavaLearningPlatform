package xyz.wochib70.jimmer.integration.springboot;


import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.runtime.ConnectionManager;
import org.babyfish.jimmer.sql.runtime.Executor;
import org.babyfish.jimmer.sql.runtime.SqlFormatter;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.function.Function;

@Configuration
public class JiimerConfiguration {

    private final static Logger LOGGER = LoggerFactory.getLogger(JiimerConfiguration.class);

    @Bean
    public JSqlClient jSqlClient(DataSource dataSource) {
        return JSqlClient.newBuilder()
                .setConnectionManager(new ConnectionManager() {
                    @Override
                    public <R> R execute(@Nullable Connection con, Function<Connection, R> block) {
                        Connection connection = DataSourceUtils.getConnection(dataSource);
                        try {
                            return block.apply(connection);
                        } finally {
                            DataSourceUtils.releaseConnection(connection, dataSource);
                        }
                    }
                })
                .setExecutor(Executor.log())
                .setSqlFormatter(SqlFormatter.INLINE_PRETTY)
                .build();
    }
}
