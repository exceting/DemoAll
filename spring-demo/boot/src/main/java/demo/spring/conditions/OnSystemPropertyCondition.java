package demo.spring.conditions;

import demo.spring.annos.ConditionalOnSystemProperty;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

public class OnSystemPropertyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        Map<String, Object> attrs = metadata.getAnnotationAttributes(ConditionalOnSystemProperty.class.getName());

        String key = String.valueOf(attrs.get("key"));
        String value = String.valueOf(attrs.get("value"));

        String systemValue = System.getProperty(key);

        return value.equals(systemValue);
    }
}
