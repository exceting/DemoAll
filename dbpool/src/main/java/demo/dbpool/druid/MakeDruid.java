/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.dbpool.druid;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.SQLException;

/**
 * @author sunqinwen
 * @version \: MakeDruid.java,v 0.1 2019-01-29 15:27
 */
public class MakeDruid {

    public static DruidDataSource makeDruidDatasource() throws SQLException {
        return makeDruidDatasource("kabii_db", 2, 2);
    }

    public static DruidDataSource makeDruidDatasource(String db, int max, int min) throws SQLException {
        DruidDataSource druid = new DruidDataSource();
        druid.setUrl(String.format("jdbc:mysql://127.0.0.1:3306/%s?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&autoReconnect=true&zeroDateTimeBehavior=convertToNull", db));
        druid.setDriverClassName("com.mysql.jdbc.Driver");
        druid.setUsername("root");
        druid.setPassword("sun123456");
        druid.setValidationQuery("SELECT 1");
        druid.setTimeBetweenLogStatsMillis(1000);
        druid.setInitialSize(0);
        druid.setTestWhileIdle(true);
        druid.setTimeBetweenEvictionRunsMillis(60000);
        druid.setQueryTimeout(1);
        druid.setMaxActive(max);
        druid.setMinIdle(min);
        druid.setMaxWait(1000L);
        //druid.init();
        return druid;
    }

}
