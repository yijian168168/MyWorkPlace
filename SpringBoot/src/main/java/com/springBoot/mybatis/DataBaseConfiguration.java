package com.springBoot.mybatis;

/**
 * 配置数据源
 * Created by Administrator on 2015/11/5 0005.
 */

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataBaseConfiguration implements EnvironmentAware {

    private static Logger log = LoggerFactory.getLogger(DataBaseConfiguration.class);

    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment env) {
        this.propertyResolver = new RelaxedPropertyResolver(env,"jdbc.");
    }

    /**配置写入的数据源*/
    @Bean(name = "writeDataSource")
    @Primary
    public DataSource writeDataSource() {

        log.debug("Configruing Write DataSource");
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setUrl(propertyResolver.getProperty("url"));
        datasource.setDriverClassName(propertyResolver.getProperty("driverClassName"));
        datasource.setUsername(propertyResolver.getProperty("username"));
        datasource.setPassword(propertyResolver.getProperty("password"));

        return datasource;
    }

    /**配置读的数据源*/
    @Bean(name = "readDataSource")
    public DataSource readDataSource() {

        log.debug("Configruing Read One DataSource");
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setUrl(propertyResolver.getProperty("url"));
        datasource.setDriverClassName(propertyResolver.getProperty("driverClassName"));
        datasource.setUsername(propertyResolver.getProperty("username"));
        datasource.setPassword(propertyResolver.getProperty("password"));

        return datasource;
    }

}
