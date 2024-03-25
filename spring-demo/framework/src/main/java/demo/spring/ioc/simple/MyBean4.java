package demo.spring.ioc.simple;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBean4 {

    public MyBean4() {
        System.out.println("MyBean4 实例化......");
    }

    @Bean
    public MyBean4 getMyBean4() {
        return new MyBean4();
    }

}
