package demo.spring.service.impl;

import demo.spring.service.CalService;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Service;

//@Profile("j7")
@Service
@Conditional(J7CalService.J7Condition.class)
@ComponentScan(basePackages = "demo.spring.service")
public class J7CalService implements CalService {
    @Override
    public Integer sum(Integer... values) {
        int sum = 0;
        for (Integer value : values) {
            sum += value;
        }
        return sum;
    }

    static class J7Condition implements Condition{

        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return true;
        }
    }
}
