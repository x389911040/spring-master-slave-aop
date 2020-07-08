package com.xue.aop.service;

import com.xue.aop.entity.User;

import java.util.List;

/**
 * @author xuejh
 * @description 用户服务接口
 * @create 2020-07-07 11:02
 **/
public interface UserService {

    /**
     * 添加用户
     * @author xuejh
     * @create 2020/7/7 15:21
     * @param user
     * @return void
     */
    void addUser(User user);

    /**
     * 获取用户列表
     * @author xuejh
     * @create 2020/7/7 15:21
     * @param
     * @return java.util.List<com.xue.aop.entity.User>
     */
    List<User> getUserList();

    /**
     * 通过Id查找用户     * @author xuejh
     * @create 2020/7/8 18:27
     * @param user
     * @return com.xue.aop.entity.User
     */
    User findUserById(User user);
}
