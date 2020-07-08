package com.xue.aop.service.impl;

import com.xue.aop.common.annotations.DBSlave;
import com.xue.aop.entity.User;
import com.xue.aop.mapper.UserMapper;
import com.xue.aop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xuejh
 * @description 用户服务实现
 * @create 2020-07-07 11:03
 **/
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    @DBSlave
    public User findUserById(User user) {
        return userMapper.findUserById(user);
    }
}
