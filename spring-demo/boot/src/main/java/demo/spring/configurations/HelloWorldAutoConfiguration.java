package demo.spring.configurations;

import demo.spring.annos.ConditionalOnSystemProperty;
import demo.spring.annos.EnableHelloWorld;
import org.springframework.context.annotation.Configuration;

//@Configuration // 模式注解装配
@EnableHelloWorld // @Enable模块装配
@ConditionalOnSystemProperty(key = "user.name", value = "butersam") // 条件装配
public class HelloWorldAutoConfiguration {
}
