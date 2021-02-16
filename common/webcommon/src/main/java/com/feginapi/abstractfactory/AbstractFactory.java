package com.feginapi.abstractfactory;


import com.feginapi.config.DruidPropertyConfig;

/**
 * 抽象工厂方法
 */
public interface AbstractFactory<T> {
    /**
     * 创建durid数据源
     * @return
     */
    T createDuridDataSource(DruidPropertyConfig propertyConfig)throws Exception ;
}
