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
@MapperScan(basePackages = "demo.sharemer.mapper.quora", sqlSessionFactoryRef = "quoraSqlSession", annotationClass = Mapper.class)
public class QuoraDatasourceConfig {

    @ConfigurationProperties(prefix = "mysql.quora")
    @Bean(name = "quoraDataSource", destroyMethod = "close")
    public DruidDataSource quoraDataSource() {
        return new DruidDataSource();
    }


    @Bean(name = "quoraSqlSession")
    public SqlSessionFactory quoraSqlSession(@Qualifier("quoraDataSource") DataSource quoraDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(quoraDataSource);
        return bean.getObject();
    }

    @Bean(name = "quoraTransactionManager")
    public PlatformTransactionManager quoraTransactionManager(@Qualifier("quoraDataSource") DataSource quoraDataSource) {
        return new DataSourceTransactionManager(quoraDataSource);
    }

}
