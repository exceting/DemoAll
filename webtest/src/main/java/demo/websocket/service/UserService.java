/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.websocket.service;

import demo.websocket.mapper.UserMapper;
import demo.websocket.po.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author sunqinwen
 * @version \: UserService.java,v 0.1 2019-03-01 15:29
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    //被spring事务代理后，在执行该方法之前会先获取一个connection对象，然后setAutoCommit(false)
    //运行结束后决定commit还是rollback，最后close归还连接给连接池
    @Transactional
    public void saveUsers(Integer rb) {
        User user = new User();
        user.setBirth(new Date());
        user.setIs_married(0);
        user.setPassword("adsfs");
        user.setAvater("sss");
        user.setAddress("China");
        user.setName("Sun1");
        user.setCreate_time(new Date());

        userMapper.insert(user);

        new Thread(()->{
            //独立的栈帧，此时会重新从连接池获取一个connection对象
            System.out.println("子线程insert1----------");
            User user2 = new User();
            user2.setBirth(new Date());
            user2.setIs_married(0);
            user2.setPassword("adsfs2");
            user2.setAvater("sss2");
            user2.setAddress("China2");
            user2.setName("Sun2");
            user2.setCreate_time(new Date());

            userMapper.insert(user2);

            System.out.println("子线程insert2----------");

        }).start();

        System.out.println("父线程insert----------");
        if (rb == 1) {
            Integer a = null;
            a.equals(1);
        }

    }

}
