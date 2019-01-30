/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.dbpool.controller;

import demo.dbpool.dao.UserMapper;
import demo.dbpool.po.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sunqinwen
 * @version \: TestController.java,v 0.1 2019-01-29 15:31
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private UserMapper userMapper;

    @GetMapping("/get_user")
    public User getUser(@RequestParam Integer id) {
        return userMapper.one(id);
    }


}
