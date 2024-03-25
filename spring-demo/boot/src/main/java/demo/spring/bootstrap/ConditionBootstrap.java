package demo.spring.bootstrap;

import demo.spring.annos.ConditionalOnSystemProperty;
import demo.spring.service.CalService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "demo.spring")
public class ConditionBootstrap {

    @Bean
    @ConditionalOnSystemProperty(key = "user.name", value = "sunqinwen")
    public String condition1() {
        return "condition 1 true!";
    }

    @Bean
    @ConditionalOnSystemProperty(key = "user.name", value = "butersam")
    public String condition2() {
        return "condition 2 true!";
    }

    // onBean是依靠containsBeanDefinition生效的，这里condition5比condition3提前被ConfigurationClassProcessor加载到，
    // 因此OnBean无法匹配condition3，condition5也就被skip了
    @Bean
    @ConditionalOnBean(name = "condition3")
    public String condition5() {
        return "condition 5 true!";
    }

    @Bean
    @ConditionalOnMissingBean(name = "condition3")
    public String condition6() {
        return "condition 6 true!";
    }

    @Bean
    @ConditionalOnClass(name = "demo.spring.bootstrap.ConditionBootstrap")
    public String condition3() {
        return "condition 3 true!";
    }

    @Bean
    @ConditionalOnMissingClass("demo.spring.bootstrap.ConditionBootstrap")
    public String condition4() {
        return "condition 4 true!";
    }


    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ConditionBootstrap.class)
                .web(WebApplicationType.NONE)
                .profiles("j8")
                .run(args);

        //String c1 = context.getBean("condition1", String.class);

        //System.out.println("----------->   c1 = " + c1);

        System.out.println("----------->   contains c1 = " + context.containsBean("condition1"));
        System.out.println("----------->   contains c2 = " + context.containsBean("condition2"));
        System.out.println("----------->   contains c3 = " + context.containsBean("condition3"));
        System.out.println("----------->   contains c4 = " + context.containsBean("condition4"));
        System.out.println("----------->   contains c5 = " + context.containsBean("condition5"));
        System.out.println("----------->   contains c6 = " + context.containsBean("condition6"));

        context.close();
    }
}
