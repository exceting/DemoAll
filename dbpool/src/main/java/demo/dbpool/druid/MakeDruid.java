/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.dbpool.druid;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author sunqinwen
 * @version \: MakeDruid.java,v 0.1 2019-01-29 15:27
 */
public class MakeDruid {

    public static DruidDataSource makeDruidDatasource() {
        DruidDataSource druid = new DruidDataSource();
        druid.setUrl("jdbc:mysql://127.0.0.1:3306/kabii_db?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&autoReconnect=true&zeroDateTimeBehavior=convertToNull");
        druid.setDriverClassName("com.mysql.jdbc.Driver");
        druid.setUsername("root");
        druid.setPassword("sun123456");
        druid.setValidationQuery("SELECT 1");
        druid.setTimeBetweenLogStatsMillis(1000);
        druid.setInitialSize(1);
        druid.setRemoveAbandoned(true);
        druid.setTestOnBorrow(true);
        druid.setTimeBetweenEvictionRunsMillis(60000);
        druid.setQueryTimeout(1);
        druid.setMaxActive(2);
        druid.setMinIdle(2);
        druid.setMaxWait(1000L);
        return druid;
    }

}
