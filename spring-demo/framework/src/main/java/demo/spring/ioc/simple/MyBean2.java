package demo.spring.ioc.simple;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class MyBean2 implements InitializingBean, BeanPostProcessor {

    @Autowired
    private MyBean1 myBean1;

    @Autowired
    private Properties systemProperties; // 内建依赖

    @Autowired
    private BeanFactory beanFactory; // 内建非Bean

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${myBean2.name}")
    private String name;

    @Value("${myBean2.age}")
    private String age;

    public MyBean2() {
        System.out.println("MyBean2 实例化......");
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("MyBean2=========>  " + systemProperties + "       bf = " + (beanFactory == null ? null : beanFactory.hashCode()) + "      ac = " + (applicationContext == null ? null : applicationContext.hashCode()));
        System.out.println("MyBean2 初始化......");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("MyBean2=========>  postProcessBeforeInitialization " + systemProperties + "       bf = " + (beanFactory == null ? null : beanFactory.hashCode()) + "      ac = " + (applicationContext == null ? null : applicationContext.hashCode()));
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("MyBean2=========>  postProcessAfterInitialization " + systemProperties + "       bf = " + (beanFactory == null ? null : beanFactory.hashCode()) + "      ac = " + (applicationContext == null ? null : applicationContext.hashCode()));
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    @Override
    public String toString() {
        return "MyBean2{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public MyBean1 getMyBean1() {
        return myBean1;
    }
}
