package demo.spring.bootstrap;

import demo.spring.annos.EnableHelloWorld;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@EnableHelloWorld
//@ComponentScan(basePackages = "demo.spring")
public class HelloWorldBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(HelloWorldBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        String hw = context.getBean("helloWorld", String.class);

        System.out.println("----------->   hw = " + hw);

        context.close();
    }

}
