package demo.spring.ioc;

import demo.spring.ioc.importer.MyImportBeanDefinitionRegistrar;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.*;

@Configuration
//@ComponentScan(basePackages = "demo.spring")
//@Import(Importer.class)
@Import(MyImportBeanDefinitionRegistrar.class)
public class Config implements BeanPostProcessor {

    @Bean(name = "address2")
        //@Scope("prototype")
    Address getAddress2() {
        System.out.println("---------  " + getAddress1().hashCode());
        Address address2 = new Address();
        address2.setCode(2);
        address2.setAddr("Beijing");
        return address2;
    }

    @Bean(name = "address1")
        //@Scope("prototype")
    Address getAddress1() {
        System.out.println("iiiiiii");
        Address address1 = new Address();
        address1.setCode(1);
        address1.setAddr("Shanghai");
        return address1;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("++++++++++++ Bean 初始化前处理器，beanName = " + beanName + "   bean = " + bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("++++++++++++ Bean 初始化后处理器，beanName = " + beanName + "   bean = " + bean);
        return bean;
    }
}
