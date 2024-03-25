package demo.spring.ioc.simple;

import demo.spring.ioc.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
//@PropertySource()
//@PropertySources()
public class MyBean5 implements InitializingBean, BeanPostProcessor {

    @Resource
    private MyBean4 myBean4;

    @Autowired
    private User userInStudent;

    @Autowired
    private MyBean6 myBean6;

    public MyBean5(){}

    @Autowired
    public MyBean5(MyBean4 myBean4) {
        this.myBean4 = myBean4;
    }

    //@Autowired
    public MyBean5(MyBean4 myBean4, MyBean6 myBean6) {
        Integer a = 5;
        Integer.valueOf(6);
        this.myBean4 = myBean4;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("^^^^^^^^^^^^^ Bean 初始化前处理器，beanName = " + beanName + "   bean = " + bean + "   myBean6 = " + myBean6);
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("^^^^^^^^^^^^^ Bean 初始化后处理器，beanName = " + beanName + "   bean = " + bean + "   myBean6 = " + myBean6);
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("##########  " + userInStudent.hashCode());
    }
}
