package com.webcommon.abstractfactory.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.webcommon.abstractfactory.AbstractFactory;
import com.webcommon.config.DruidPropertyConfig;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Druid工厂方法主要生产druid数据源
 */
@Component
public class DruidFactory implements AbstractFactory<DruidDataSource> {
    public DruidDataSource createDuridDataSource(DruidPropertyConfig propertyConfig)throws Exception {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setInitialSize(propertyConfig.getInitialSize());
        druidDataSource.setMinIdle(propertyConfig.getMinIdle());
        druidDataSource.setMaxActive(propertyConfig.getMaxActive());
        druidDataSource.setMaxWait(propertyConfig.getMaxWait());
        druidDataSource.setTimeBetweenEvictionRunsMillis(propertyConfig.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(propertyConfig.getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(propertyConfig.getValidationQuery());
        druidDataSource.setTestWhileIdle(propertyConfig.getTestWhileIdle());
        druidDataSource.setTestOnBorrow(propertyConfig.getTestOnBorrow());
        druidDataSource.setTestOnReturn(propertyConfig.getTestOnReturn());
        druidDataSource.setPoolPreparedStatements(propertyConfig.getPoolPreparedStatements());
        druidDataSource.setFilters(propertyConfig.getFilters());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(propertyConfig.getMaxPoolPreparedStatementPerConnectionSize());
        druidDataSource.setUseGlobalDataSourceStat(propertyConfig.getUseGlobalDataSourceStat());
        Properties properties = new Properties();
        properties.setProperty("druid.stat.mergeSql","true");
        properties.setProperty("druid.stat.slowSqlMillis","500");
        druidDataSource.setConnectProperties(properties);
        return druidDataSource;
    }
}
