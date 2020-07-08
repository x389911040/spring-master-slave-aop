package com.xue.aop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AopApplicationTests {

    @Value("${mybatis.type-aliases-package}")
    private String typeAliases;

    @Test
    void contextLoads() {
        System.out.println("\"" + typeAliases + "\"");
    }

}
