package com.example.springbootAll.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @Author: sea
 * @Description: 数据源配置
 * @Date: 13:39 2017/8/12
 */
@Configuration
public class DataSourceConfig {

    //主数据源
    @Bean(name = "priDataSource") //装配该方法返回值为priDataSource管理Bean
    @Qualifier("priDataSource") //Spring装配bean的唯一标识
    @Primary // 配置该数据源为从数据源 当没有配置自动切换的package时默认使用该数据源进行数据处理操作。
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource priDataSource(){
        return DataSourceBuilder.create().build();
    }

    //从数据源
    @Bean(name = "secDataSource") //装配该方法返回值为priDataSource管理Bean
    @Qualifier("secDataSource") //Spring装配bean的唯一标识
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secDataSource(){
        return DataSourceBuilder.create().build();
    }
}
