import demo.spring.aop.aspectj.AopService;
import demo.spring.aop.spring.EchoService;
import demo.spring.aop.spring.EchoServicePointcut;
import demo.spring.aop.trace.OrderService;
import demo.spring.aop.trace.UserService;
import demo.spring.aop.trace.advices.LogArgsAdvice;
import demo.spring.aop.trace.advices.LogResultAdvice;
import demo.spring.aop.transaction.TransactionService;
import demo.spring.ioc.Config2;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class TestAOP {

    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config2.class);
        AopService aopService = context.getBean(AopService.class);
        aopService.m1();
    }

    @Test
    public void test2() {

        Map<String, Object> cache = new HashMap<>();

        AspectJProxyFactory factory = new AspectJProxyFactory(cache);
        //factory.addAspect(Agent.class);
        factory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                if ("put".equals(method.getName()) && args.length == 2) {
                    System.out.println("Put Before: Current key = " + args[0] + "    value = " + args[1]);
                }
            }
        });

        factory.addAdvice(new AfterReturningAdvice() {
            @Override
            public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
                if ("put".equals(method.getName()) && args.length == 2) {
                    System.out.println("Put After returning: Current key = " + args[0] + "    value = " + args[1]);
                }
            }
        });

        Map<String, Object> proxyMap = factory.getProxy();

        proxyMap.put("sun", "haha");

    }

    @Test
    public void test3() {
        EchoServicePointcut echoServicePointcut = new EchoServicePointcut();

        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor(echoServicePointcut, new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                System.out.println("=========> iiiiiiinvoke!!!!");
                return invocation.proceed();
            }
        });

        EchoService echoService = new EchoService();

        ProxyFactory proxyFactory = new ProxyFactory(echoService);
        proxyFactory.addAdvisor(defaultPointcutAdvisor);

        EchoService proxy = (EchoService) proxyFactory.getProxy();

        proxy.echo();
    }

    @Test
    public void test4() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
    }

    @Test
    public void test5() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(Config2.class);

        context.setAllowCircularReferences(true);

        context.refresh();

        UserService userService = context.getBean(UserService.class);
        OrderService orderService = context.getBean(OrderService.class);

        userService.createUser("Tom", "Cruise", 55);
        userService.queryUser();

        orderService.createOrder("Leo", "随便买点什么");
        orderService.queryOrder("Leo");

        AopService aopService = context.getBean(AopService.class);
        aopService.m1();
        aopService.m2();

        TransactionService transactionService = context.getBean(TransactionService.class);
        System.out.println("==========>  " + transactionService);
    }
}
