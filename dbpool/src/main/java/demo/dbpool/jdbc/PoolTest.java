/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.dbpool.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import demo.dbpool.druid.MakeDruid;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author sunqinwen
 * @version \: PoolTest.java,v 0.1 2019-08-20 18:56
 */
public class PoolTest {
    public static void main(String[] args) throws Exception {
        DruidDataSource druid = MakeDruid.makeDruidDatasource("pugv_pay", 1, 1);

            int i = 0;
            while (i < 100) {
                DruidPooledConnection conn = druid.getConnection();
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("select id FROM t_pay_order_201908 WHERE id = 1");
                resultSet.next();
                int id = resultSet.getInt("id");
                System.out.println("id = " + id);
                i++;
                if (i == 60) {
                    System.out.println("已经过了60s辣~");
                }
                statement.close();
                conn.close();
                Thread.sleep(100000L);
            }

    }
}
