package com.xue.aop;

import com.xue.aop.common.aop_impl.DataSourceConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;


@Import(DataSourceConfig.class)
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.xue.aop.mapper")
public class AopApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);
    }

}
