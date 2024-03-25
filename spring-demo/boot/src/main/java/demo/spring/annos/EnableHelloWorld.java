package demo.spring.annos;

import demo.spring.configurations.HelloWorldConfiguration;
import demo.spring.selectors.HelloWorldImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(HelloWorldConfiguration.class) // 直接注入HelloWorldConfiguration
//@Import(HelloWorldImportSelector.class)  // 通过ImportSelector注入HelloWorldConfiguration
public @interface EnableHelloWorld {
}
