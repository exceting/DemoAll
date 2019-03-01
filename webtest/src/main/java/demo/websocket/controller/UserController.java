/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.websocket.controller;

import demo.websocket.jdbc.UserDao;
import demo.websocket.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sunqinwen
 * @version \: UserController.java,v 0.1 2019-03-01 15:31
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserDao userDao;

    @PostMapping("save_users")
    public String saveUsers(@RequestParam Integer rollback) {
        try {
            userService.saveUsers(rollback);
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            return "FAIL";
        }
    }

    @PostMapping("save_users_v2")
    public String saveUsersV2(@RequestParam Integer rollback) {
        try {
            userDao.saveUsers(rollback);
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            return "FAIL";
        }
    }

}
