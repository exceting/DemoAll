package demo.spring.aop.aspectj;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Agent {

    @Before("execution(* demo.spring.aop.aspectj.*.*(..))")
    public void before() {
        System.out.println("Agent: before done.");
    }

    @AfterReturning("execution(* demo.spring.aop.aspectj.*.*(..))")
    public void afterReturning() {
        System.out.println("Agent: after returning done.");
    }

    @AfterThrowing("execution(* demo.spring.aop.aspectj.*.*(..))")
    public void afterThrowing() {
        System.out.println("Agent: after throwing done.");
    }

    @After("execution(* demo.spring.aop.aspectj.*.*(..))")
    public void after() {
        System.out.println("Agent: after done.");
    }
}
