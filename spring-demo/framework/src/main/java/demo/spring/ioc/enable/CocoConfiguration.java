package demo.spring.ioc.enable;

import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Configuration
@Conditional(value = CocoConfiguration.Matcher.class)
public class CocoConfiguration {

    @Bean
    public Coco coco() {
        return new Coco();
    }

    static class Matcher implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return context.getBeanFactory().containsBean("haha");
        }
    }

}
