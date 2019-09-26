package demo.dbpool.hikari;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

public class HikariTest {

    public static void main(String[] args) throws Exception {
        HikariDataSource hikariDataSource = MakeHikari.makeHikariDatasource();
        Connection connection = hikariDataSource.getConnection();
        Thread.sleep(1000L);
        connection.close();
    }

}
