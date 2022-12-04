package demo.sharemer.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "demo.sharemer.mapper.worlds", sqlSessionFactoryRef = "worldsSqlSession", annotationClass = Mapper.class)
public class WorldsDatasourceConfig {

    @ConfigurationProperties(prefix = "mysql.worlds")
    @Bean(name = "worldsDataSource", destroyMethod = "close")
    public DruidDataSource worldsDataSource() {
        return new DruidDataSource();
    }


    @Bean(name = "worldsSqlSession")
    public SqlSessionFactory worldsSqlSession(@Qualifier("worldsDataSource") DataSource worldsDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(worldsDataSource);
        return bean.getObject();
    }

    @Bean(name = "worldsTransactionManager")
    public PlatformTransactionManager worldsTransactionManager(@Qualifier("worldsDataSource") DataSource worldsDataSource) {
        return new DataSourceTransactionManager(worldsDataSource);
    }

}
