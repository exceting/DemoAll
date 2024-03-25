package demo.spring.ioc;

import demo.spring.aop.trace.advices.LogArgsAdvice;
import demo.spring.aop.trace.advices.LogResultAdvice;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(basePackages = "demo.spring")
@EnableAspectJAutoProxy // see: AnnotationAwareAspectJAutoProxyCreator
@EnableTransactionManagement
@PropertySource("classpath:/my.properties")
//@Configuration
public class Config2 {

    @Bean(name = "student233", initMethod = "init")
    public Student getStudent() {
        Student student = new Student();
        student.setAge(2333333);
        student.setName("2333333");
        return student;
    }

   /* @Bean(name = "objectFactory")
    public ObjectFactoryCreatingFactoryBean getObjectFactory() {
        ObjectFactoryCreatingFactoryBean objectFactoryCreatingFactoryBean = new ObjectFactoryCreatingFactoryBean();
        objectFactoryCreatingFactoryBean.setTargetBeanName("student233");
        return objectFactoryCreatingFactoryBean;
    }*/

    @Bean
    public LogArgsAdvice logArgsAdvice(){
        return new LogArgsAdvice();
    }

    @Bean
    public LogResultAdvice logResultAdvice(){
        return new LogResultAdvice();
    }

    @Bean
    public RegexpMethodPointcutAdvisor logArgsAdvisor(LogArgsAdvice logArgsAdvice) {
        RegexpMethodPointcutAdvisor regexpMethodPointcutAdvisor = new RegexpMethodPointcutAdvisor();
        regexpMethodPointcutAdvisor.setAdvice(logArgsAdvice);
        regexpMethodPointcutAdvisor.setPattern("demo.spring.aop.trace.impl.*.create.*");
        return regexpMethodPointcutAdvisor;
    }

    @Bean
    public RegexpMethodPointcutAdvisor logResultAdvisor(LogResultAdvice logResultAdvice) {
        RegexpMethodPointcutAdvisor regexpMethodPointcutAdvisor = new RegexpMethodPointcutAdvisor();
        regexpMethodPointcutAdvisor.setAdvice(logResultAdvice);
        regexpMethodPointcutAdvisor.setPattern("demo.spring.aop.trace.impl.*.query.*");
        return regexpMethodPointcutAdvisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        return new DefaultAdvisorAutoProxyCreator();
    }

}
