/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.dbpool.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author sunqinwen
 * @version \: JdbcTest.java,v 0.1 2019-08-20 15:46
 */
public class JdbcTest {

    public static void main(String[] args) throws Exception {
        //Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/pugv_pay?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&autoReconnect=true&zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String password = "sun123456";
        //建立连接
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement statement = conn.createStatement();
        try {
            int i = 0;
            while (i < 100) {
                ResultSet resultSet = statement.executeQuery("select id FROM t_pay_order_201908 WHERE id = 1");
                resultSet.next();
                int id = resultSet.getInt("id");
                System.out.println("id = " + id);
                i++;
                if (i == 60) {
                    System.out.println("已经过了60s辣~");
                }
                Thread.sleep(100000L);
            }
        } finally {
            statement.close();
            conn.close();
        }
    }

}
