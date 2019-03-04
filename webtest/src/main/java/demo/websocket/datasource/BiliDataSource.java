/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2018 All Rights Reserved.
 */

package demo.websocket.datasource;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * @author Juwin.S
 * @version \: BiliDataSource.java,v 0.1 2018-05-03 18:47
 */
public class BiliDataSource implements DataSource {

    private DruidDataSource druidDataSource = new DruidDataSource();

    public BiliDataSource() {
        //基础配置信息设置默认值
        this.setValidationQuery("SELECT 1")
            .setTimeBetweenLogStatsMillis(10000);
        this.setRemoveAbandoned(true);
        this.setTestOnBorrow(true);
        this.setTimeBetweenEvictionRunsMillis(60000);
        this.setQueryTimeout(1);
    }

    public void setDriverClassName(String driver) {
        druidDataSource.setDriverClassName(driver);
    }

    public void setUrl(String url) {
        druidDataSource.setUrl(url);
    }

    public void setUsername(String userName) {
        druidDataSource.setUsername(userName);
    }

    public void setPassword(String pwd) {
        druidDataSource.setPassword(pwd);
    }

    public void setMaxActive(int maxTotal) {
        druidDataSource.setMaxActive(maxTotal);
    }

    public void setMaxWait(int maxWait) {
        druidDataSource.setMaxWait(maxWait);
    }

    public void setMaxIdle(int maxIdle) {
        druidDataSource.setMaxIdle(maxIdle);
    }

    public void setMinIdle(int minIdle) {
        druidDataSource.setMinIdle(minIdle);
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        druidDataSource.setTestOnBorrow(testOnBorrow);
    }

    public void setRemoveAbandoned(boolean removeAbandonedOn) {
        druidDataSource.setRemoveAbandoned(removeAbandonedOn);
    }

    public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
        druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
    }

    public void setQueryTimeout(int queryTimeout) {
        druidDataSource.setQueryTimeout(queryTimeout);
    }
    //不可定制属性

    private BiliDataSource setValidationQuery(String validationQuery) {
        druidDataSource.setValidationQuery(validationQuery);
        return this;
    }

    private BiliDataSource setTimeBetweenLogStatsMillis(long timeBetweenLogStatsMillis) {
        druidDataSource.setTimeBetweenLogStatsMillis(timeBetweenLogStatsMillis);
        return this;
    }

    public BiliDataSource setInitialSize(int initialSize) {
        druidDataSource.setInitialSize(initialSize);
        return this;
    }


    @Override
    public Connection getConnection() throws SQLException {
        System.out.println("-------------------------获取链接-----当前线程："+Thread.currentThread().getName());
        return druidDataSource.getConnection();
    }

    @Override
    public Connection getConnection(String username, String password)
        throws SQLException {
        return druidDataSource.getConnection(username, password);
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return druidDataSource.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return druidDataSource.isWrapperFor(iface);
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return druidDataSource.getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        druidDataSource.setLogWriter(out);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return druidDataSource.getLoginTimeout();
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        druidDataSource.setLoginTimeout(seconds);
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return druidDataSource.getParentLogger();
    }

    public void close() {
        System.out.println("-------------------------归还链接-----当前线程："+Thread.currentThread().getName());
        druidDataSource.close();
    }
}
