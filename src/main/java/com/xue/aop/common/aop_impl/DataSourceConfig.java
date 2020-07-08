package com.xue.aop.common.aop_impl;

import com.xue.aop.common.enums.DBTypeEnum;
import com.zaxxer.hikari.util.DriverDataSource;
import jdk.nashorn.internal.ir.annotations.Reference;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 数据源配置
 * @author xuejh
 * @description
 * @create 2020-07-07 15:32
 **/
@Configuration
@Order(1)
@Data
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfig {

    private Map<String, String> write;

    private Map<String, String> read;

    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.write")
    public DataSource writeDataSource() {
//        return DataSourceBuilder.create().build();
        return new DriverDataSource(write.get("url"), write.get("driver-class-name"), new Properties(), write.get("username"), write.get("password"));
    }

    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.read")
    public DataSource readDataSource() {
//        return DataSourceBuilder.create().build();
        return new DriverDataSource(read.get("url"), read.get("driver-class-name"), new Properties(), read.get("username"), read.get("password"));
    }

    @Bean
    @Primary
    public DataSource dataSourceRouting(@Reference DataSource writeDataSource, @Reference DataSource readDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DBTypeEnum.MASTER, writeDataSource);
        targetDataSources.put(DBTypeEnum.SLAVE1, readDataSource);

        DataSourceRouting dataSourceRouting = new DataSourceRouting();
        dataSourceRouting.setTargetDataSources(targetDataSources);
        dataSourceRouting.setDefaultTargetDataSource(writeDataSource);

        // 默认访问主服务器
        DataSourceHolder.master();
        return dataSourceRouting;
    }
}
