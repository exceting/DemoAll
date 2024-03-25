package demo.spring.ioc.simple;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

@Component
public class MyBean3 implements InitializingBean, PriorityOrdered {

    @Autowired
    private MyBean6 myBean6;

    public MyBean3() {
        System.out.println("MyBean3 实例化......");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("MyBean3 初始化......"+"   myBean6 = "+myBean6);
    }

    @Override
    public int getOrder() {
        return 3;
    }
}
