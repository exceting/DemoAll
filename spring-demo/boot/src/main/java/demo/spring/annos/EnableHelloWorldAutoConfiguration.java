package demo.spring.annos;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface EnableHelloWorldAutoConfiguration {
}
