package demo.spring.ioc.simple;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

//@DependsOn("myBean2")
@Component
public class MyBean1 implements InitializingBean, DisposableBean, DestructionAwareBeanPostProcessor {

    @Autowired
    private MyBean2 myBean2;

    public MyBean1() {
        System.out.println("MyBean1 实例化......");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("MyBean1 初始化......");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("XXXXXXXXXX   myBean1 destroy 方法触发！");
    }

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        System.out.println("XXXXXXXXXX   myBean1 postProcessBeforeDestruction 方法触发！");
    }

    public MyBean2 getMyBean2() {
        return myBean2;
    }
}
