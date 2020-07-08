package com.xue.aop.common.aop_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 填充数据源
 * @author xuejh
 * @description
 * @create 2020-07-07 17:31
 **/
@Slf4j
public class DataSourceRouting extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        log.info("===============================>当前使用数据源：" + DataSourceHolder.get());
        return DataSourceHolder.get();
    }
}
