/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.dbpool.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.SQLException;

/**
 * @author sunqinwen
 * @version \: DruidTest.java,v 0.1 2019-01-24 15:08
 */
public class DruidTest {

    public static void main(String[] args) throws SQLException {
        DruidDataSource druid = new DruidDataSource();
        druid.setUrl("jdbc:mysql://127.0.0.1:3306/tree_tracer?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&autoReconnect=true&zeroDateTimeBehavior=convertToNull");
        druid.setDriverClassName("com.mysql.jdbc.Driver");
        druid.setUsername("root");
        druid.setPassword("sun123456");
        druid.setValidationQuery("SELECT 1");
        druid.setTimeBetweenLogStatsMillis(1000);
        druid.setInitialSize(5);
        druid.setRemoveAbandoned(true);
        druid.setTestOnBorrow(true);
        druid.setTimeBetweenEvictionRunsMillis(60000);
        druid.setQueryTimeout(1);
        druid.setMaxActive(2);
        druid.setMinIdle(2);
        druid.setMaxWait(1000L);

        DruidPooledConnection connection = druid.getConnection();
        DruidPooledConnection connection2 = druid.getConnection();
        System.out.println("================"+connection+"====="+connection2);

        connection.close();
        connection2.close();

        System.out.println("================"+connection+"====="+connection2);

        druid.close();

        //DruidPooledConnection connection2 = druid.getConnection();

        //System.out.println("================"+connection2);
    }

}
