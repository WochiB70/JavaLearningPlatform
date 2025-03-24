package xyz.wochib70.mybatis.spring;


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.SpringProperties;

import javax.sql.DataSource;

@PropertySource("classpath:jdbc-config.properties")
@Configuration
public class SpringApplicationConfiguration {


    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(SpringProperties.getProperty("jdbc.url"));
        dataSource.setDriverClassName(SpringProperties.getProperty("jdbc.driver"));
        dataSource.setUsername(SpringProperties.getProperty("jdbc.username"));
        dataSource.setPassword(SpringProperties.getProperty("jdbc.password"));
        return dataSource;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("xyz.wochib70.mybatis.spring.dao");
        return mapperScannerConfigurer;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return  sqlSessionFactoryBean.getObject();
    }

}
