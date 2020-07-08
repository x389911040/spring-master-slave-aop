package com.xue.aop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xuejh
 * @description 用户实体类
 * @create 2020-07-07 10:44
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private long id;

    private String name;

    private int age;
}
