package demo.spring.proxy;

import java.sql.Connection;
import java.sql.Statement;

public class MysqlTestDatasource implements TestDatasource {
    @Override
    public Connection getConnection() {
        System.out.println("Mysql get connection trigger!");
        createStatement();
        return null;
    }

    @Override
    public Statement createStatement() {
        System.out.println("Mysql create statement trigger!");
        return null;
    }
}
