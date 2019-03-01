/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.websocket.jdbc;

import demo.websocket.po.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author sunqinwen
 * @version \: UserDao.java,v 0.1 2019-03-01 16:26
 */
@Repository
public class UserDao {

    private Connection conn;

    public UserDao (){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/kabii_db?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&autoReconnect=true&zeroDateTimeBehavior=convertToNull",
                    "root", "sun123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUsers(int rb) throws Exception {

        Class.forName("com.mysql.jdbc.Driver");
        Statement statement = conn.createStatement();
        try {
            conn.setAutoCommit(false);

            User user = new User();
            user.setIs_married(0);
            user.setPassword("adsfs");
            user.setAvater("sss");
            user.setAddress("China");
            user.setName("Sun1");

            statement.executeUpdate(getSql(user));


            User user2 = new User();
            user2.setIs_married(0);
            user2.setPassword("adsfs2");
            user2.setAvater("sss2");
            user2.setAddress("China2");
            user2.setName("Sun2");

            statement.executeUpdate(getSql(user2));

            if (rb == 1) {
                Integer a = null;
                a.equals(1);
            }

            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.rollback();
            statement.close();
        }


    }

    private String getSql(User user) {
        return "insert into tb_user (`name`, password, birth, address, avater, is_married, create_time) values ('"
                + user.getName() + "', '"
                + user.getPassword() + "', '"
                + "2003-01-01" + "', '"
                + user.getAddress() + "', '"
                + user.getAvater() + "', "
                + user.getIs_married() + ", '"
                + "2019-03-01 00:00:00'" + ")";
    }

}
