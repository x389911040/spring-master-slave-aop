package com.xue.aop.controller;

import com.xue.aop.entity.User;
import com.xue.aop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xuejh
 * @description 用户测试入口类
 * @create 2020-07-07 10:28
 **/
@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("test")
    public String test() {
        return "hello test....";
    }

    /**
     * 添加用户
     * @author xuejh
     * @create 2020/7/7 11:05
     * @param user
     * @return void
     */
    @PostMapping("add")
    public String addUser(@RequestBody User user) throws Exception {
        userService.addUser(user);
        return "添加用户成功";
    }

    /**
     * 获取用户列表
     * @author xuejh
     * @create 2020/7/7 15:20
     * @param
     * @return java.util.List<com.xue.aop.entity.User>
     */
    @GetMapping("list")
    public List<User> getUserList() {
        return userService.getUserList();
    }

    @GetMapping("userById")
    public User findUserById(@RequestBody User user) {
        return userService.findUserById(user);
    }
}
