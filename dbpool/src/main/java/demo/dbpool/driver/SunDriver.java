package demo.dbpool.driver;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Create by 18073 on 2019/9/14.
 */
public class SunDriver implements Driver {
    /*static {
        try {
            DriverManager.registerDriver(new SunDriver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        return null;
    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return false;
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return new DriverPropertyInfo[0];
    }

    @Override
    public int getMajorVersion() {
        return 0;
    }

    @Override
    public int getMinorVersion() {
        return 0;
    }

    @Override
    public boolean jdbcCompliant() {
        return false;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
