package demo.spring.proxy;

import java.sql.Connection;
import java.sql.Statement;

public interface TestDatasource {

    Connection getConnection();

    Statement createStatement();

}
