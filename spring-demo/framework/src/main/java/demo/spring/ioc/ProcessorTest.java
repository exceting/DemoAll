package demo.spring.ioc;

import demo.spring.aop.aspectj.Agent;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;

@Component
public class ProcessorTest implements InstantiationAwareBeanPostProcessor, MergedBeanDefinitionPostProcessor {
    @Override
    public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
        if (beanType == Agent.class) {
            System.out.println("*********  ProcessorTest#postProcessMergedBeanDefinition");
        }
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (beanClass == Agent.class) {
            System.out.println("*********  ProcessorTest#postProcessBeforeInstantiation");
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (bean instanceof Agent) {
            System.out.println("*********  ProcessorTest#postProcessAfterInstantiation");
        }
        return true;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        if (bean instanceof Agent) {
            System.out.println("*********  ProcessorTest#postProcessPropertyValues");
        }
        return pvs;
    }
}
