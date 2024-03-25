package demo.spring.ioc.simple;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MyBean6 implements InitializingBean, BeanPostProcessor {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("=========> myBean6 afterPropertiesSet 触发！");
    }
}
