package com.xue.aop.mapper;

import com.xue.aop.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xuejh
 * @description 用户Mapper类
 * @create 2020-07-07 13:43
 **/
@Mapper
public interface UserMapper {

    void addUser(User user);

    List<User> getUserList();

    User findUserById(User user);
}
