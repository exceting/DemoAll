/**
 * sharemer.com Inc.
 * Copyright (c) 2018-2019 All Rights Reserved.
 */

package demo.dbpool.config;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Objects;

/**
 * @author sunqinwen
 * @version \: AppConfig.java,v 0.1 2018-08-15 16:44
 */
@Configuration
@ComponentScan(basePackages = {
        "demo.dbpool"
})
public class AppConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        Resource[] resources = new Resource[]{
                new ClassPathResource("app.yml")
        };
        yamlPropertiesFactoryBean.setResources(resources);
        properties.setProperties(Objects.requireNonNull(yamlPropertiesFactoryBean.getObject()));
        return properties;
    }

    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>();
        bean.setServlet(new StatViewServlet());
        bean.addUrlMappings("/druid/*");
        return bean;
    }

}
