package demo.dbpool.config;

import com.alibaba.druid.pool.DruidDataSource;
import demo.dbpool.druid.MakeDruid;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.sql.SQLException;

@Configuration
@MapperScan(basePackages = "demo.dbpool", sqlSessionFactoryRef = "sqlSession", annotationClass = Mapper.class)
public class DataSourceConfig {

    @Primary
    @Bean(name = "dataSource", destroyMethod = "close")
    public DruidDataSource dataSource() throws SQLException {
        return MakeDruid.makeDruidDatasource();
    }

    @Bean(name = "sqlSession")
    public SqlSessionFactory sqlSession(@Qualifier("dataSource") DruidDataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

}