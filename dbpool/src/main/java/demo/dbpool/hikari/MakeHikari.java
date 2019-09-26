/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.dbpool.hikari;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.SQLException;

/**
 * @author sunqinwen
 * @version \: MakeDruid.java,v 0.1 2019-01-29 15:27
 */
public class MakeHikari {

    public static HikariDataSource makeHikariDatasource() throws SQLException {
        return makeHikariDatasource("lynx", 2, 2);
    }

    public static HikariDataSource makeHikariDatasource(String db, int max, int min) throws SQLException {
        HikariDataSource hikari = new HikariDataSource();
        hikari.setJdbcUrl(String.format("jdbc:mysql://127.0.0.1:3306/%s?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&autoReconnect=true&zeroDateTimeBehavior=convertToNull", db));
        hikari.setPoolName("testDbName");
        hikari.setDriverClassName("com.mysql.jdbc.Driver");
        hikari.setUsername("root");
        hikari.setPassword("sun123456`");
        hikari.setMaximumPoolSize(max);
        hikari.setMinimumIdle(min);
        hikari.setConnectionTimeout(200);
        hikari.setMaxLifetime(60 * 1000);
        return hikari;
    }

}
