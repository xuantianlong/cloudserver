package com.feginapi.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.feginapi.abstractfactory.impl.DruidFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {
    @Autowired
    private DruidFactory druidFactory;


    /**
     * 向spring容器注入druid数据源
     * @param jdbcProperties
     * @param druidConfigProperties
     * @return
     */
    @Bean
    public DataSource dataSource(JdbcProperties jdbcProperties, DruidPropertyConfig druidConfigProperties)throws Exception{
        DruidDataSource druidDataSource = druidFactory.createDuridDataSource(druidConfigProperties);
        druidDataSource.setDriverClassName(jdbcProperties.getDriverClassName());
        druidDataSource.setUrl(jdbcProperties.getUrl());
        druidDataSource.setUsername(jdbcProperties.getUsername());
        druidDataSource.setPassword(jdbcProperties.getPassword());
        return  druidDataSource;
    }
}
