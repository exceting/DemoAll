/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.dbpool.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * @author sunqinwen
 * @version \: DruidTest.java,v 0.1 2019-01-24 15:08
 */
public class DruidTest {

    public static void main(String[] args) throws SQLException {

        DruidDataSource druid = MakeDruid.makeDruidDatasource();

        DruidPooledConnection connection = druid.getConnection();
        //connection.close();
        //System.out.println(createOrderNo().length());
        /*DruidPooledConnection connection2 = druid.getConnection();
        System.out.println("================"+connection+"====="+connection2);

        connection.close();
        druid.close();

        Connection connection2 = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/lynx?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&autoReconnect=true&zeroDateTimeBehavior=convertToNull",
                "root", "sun123456`");

        ServiceLoader<Driver> drivers = ServiceLoader.load(Driver.class);
        for (Driver d : drivers) {
            System.out.println(d.getClass().getName());
        }*/
    }

    public static String createOrderNo() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        return String.format("%s%05d", dtf.format(LocalDateTime.now()), new Random().nextInt(99999));
    }

}
