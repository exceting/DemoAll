/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.websocket.service;

import demo.websocket.mapper.UserMapper;
import demo.websocket.po.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 *
 * @author sunqinwen
 * @version \: UserService.java,v 0.1 2019-03-01 15:29 
 *
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public void saveUsers(){
        User user = new User();
        user.setBirth(new Date());
        user.setIs_married(0);
        user.setPassword("adsfs");
        user.setAvater("sss");
        user.setAddress("China");
        user.setName("Sun1");
        user.setCreate_time(new Date());

        userMapper.insert(user);

        User user2 = new User();
        user2.setBirth(new Date());
        user2.setIs_married(0);
        user2.setPassword("adsfs2");
        user2.setAvater("sss2");
        user2.setAddress("China2");
        user2.setName("Sun2");
        user2.setCreate_time(new Date());

        userMapper.insert(user2);
    }

}
