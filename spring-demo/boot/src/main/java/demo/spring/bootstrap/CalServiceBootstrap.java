package demo.spring.bootstrap;

import demo.spring.service.CalService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "demo.spring.service")
public class CalServiceBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(CalServiceBootstrap.class)
                .web(WebApplicationType.NONE)
                .profiles("j8")
                .run(args);

        CalService calService = context.getBean(CalService.class);

        System.out.println("----------->   cal = " + calService + "      sum = " + calService.sum(1, 2, 3, 4, 5, 6, 7, 8));

        context.close();
    }

}
