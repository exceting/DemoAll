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

        DruidDataSource druid = MakeDruid.makeDruidDatasource();

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
