import demo.spring.aop.trace.impl.OrderServiceImpl;
import demo.spring.event.MyEvent;
import demo.spring.ioc.*;
import demo.spring.ioc.env.MyEnv;
import demo.spring.ioc.simple.MyBean1;
import demo.spring.ioc.simple.MyBean2;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class TestIOC {

    public static class XCXCXC {
    }

    @Test
    public void test1() throws Exception {
        //AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config2.class);
        //context.getBeanFactory().addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        //context.refresh();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(Config2.class);

        context.setAllowCircularReferences(true);

        context.refresh();

        context.addApplicationListener(event -> System.out.println("~!~!~!~!~!~   " + event + "    " + context.hashCode()));

        Map<String, User> userMap = context.getBeansOfType(User.class);
        userMap.forEach((k, v) -> {
            System.out.println("----------~~~~~~!!!  " + k + "   " + v.hashCode());
        });

        context.publishEvent(new MyEvent(this));

        MyBean1 myBean1 = context.getBean(MyBean1.class);
        System.out.println("~~~~~~~myBean1.myBean2 ========>   " + myBean1.getMyBean2());

        MyBean2 myBean2 = context.getBean(MyBean2.class);
        System.out.println("~~~~~~~myBean2.myBean1 ========>   " + myBean2.getMyBean1());

        User userInStudent = context.getBean("userInStudent", User.class);
        System.out.println("==>>>>====>>>>>  " + userInStudent);


        MyEnv myEnv = context.getBean(MyEnv.class);

        System.out.println("eeeeeee  " + (context.getEnvironment() == myEnv.getEnvironment()));

        System.out.println("eeeeeee  " + context.getEnvironment().getProperty("myBean2.age"));

        System.out.println("eeeeeee  " + context.getEnvironment().getProperty("PYCHARM_VM_OPTIONS"));

        Map<String, Object> m = context.getBean("systemEnvironment", Map.class);

        m.forEach((k, v) -> {
            System.out.println(")))))))))))    k = " + k + "     v = " + v);
        });

        //System.out.println("cocococococococo  ====== > " + context.containsBean("haha"));

        //System.out.println("cocococococococo  ====== > " + context.getBean(Coco.class));
        //context.getBean(MyBean6.class);
        //context.getBean(MyBean6.class);
        //context.getBeanFactory().registerSingleton("xcxcxc", new XCXCXC());
        //context.refresh();

        /*System.out.println("address2 = " + context.getBean("address2"));
        System.out.println("student233 = " + context.getBean("student233"));
        System.out.println("userInStudent = " + context.getBean("userInStudent"));
        System.out.println("XCXCXC = " + context.getBean("xcxcxc"));

        System.out.println("~~~~~~~~~  "+ Arrays.toString(context.getBeanNamesForType(MyBean4.class)));

        System.out.println("==========>  " + context.getBeanFactory().hashCode() + "      " + context.hashCode());*/


        //System.out.println("---------  " + context.getId());

        /*User user = context.getBean("user", User.class);
        System.out.println(user + "    " + user.hashCode());
        User user2 = context.getBean("user", User.class);
        System.out.println(user2 + "    " + user2.hashCode());

        System.out.println("******************  " + context.hashCode() + "      " + user.getBeanFactory().hashCode() + "      " + user2.getBeanFactory().hashCode());

        System.out.println("++++++++++++++++++  " + (context instanceof ApplicationContext));

        User user3 = user.getBeanFactory().getBean("user", User.class);
        System.out.println("------------------  " + user.hashCode() + "     " + user3.hashCode());

        System.out.println("&&&&&&&&&&&&&&&&&&  " + context.getClass() + "   " + user.getBeanFactory().getClass());
        //System.out.println(context.getBean(Student.class));
        System.out.println(context.getBean("student233"));

        *//*ObjectFactoryCreatingFactoryBean objectFactory = context.getBean("objectFactory", ObjectFactoryCreatingFactoryBean.class);
        System.out.println(objectFactory.getObject());*//*

        System.out.println(context.getBean("student2"));

        */

        Integer a = 200;
        Integer b = 200;
        System.out.println(a == b);

        context.close();
    }

    // 手动读取注解产生的bean信息
    @Test
    public void test2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(TestIOC.class); // 注册待扫描类
        context.refresh(); // 启动应用程序上下文
        System.out.println("---------    " + context.getBean(Student.class));
    }

    @Test
    public void test3() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config2.class);
        System.out.println("---------    " + context.getBean(StudentService.class));
        System.out.println("---------    " + context.getBean(ProcessorTest.class));
        System.out.println("---------    " + context.getBean("hhhhh"));
    }

    @Test
    public void test4() throws Exception {
        Class<OrderServiceImpl> orderServiceClass = (Class<OrderServiceImpl>) Class.forName("demo.spring.aop.trace.impl.OrderServiceImpl");
        Class<?>[] mems = orderServiceClass.getDeclaredClasses();
        for (Class<?> mc : mems) {
            System.out.println("--------  " + mc.getName());
        }

        Set<Integer> importedBy = new LinkedHashSet<>(1);
        importedBy.add(1);
        importedBy.add(2);
        System.out.println("--------  " + importedBy.size());
    }

    @Test
    public void testFactoryBeanAndObjectFactory(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestIOC.class);
        ConfigurableListableBeanFactory beanFactory = (ConfigurableListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        //注册可被注入实例
        beanFactory.registerResolvableDependency(Person.class, new PersonObjectFactory());
        //添加BeanDefinition
        RootBeanDefinition objectXDef = new RootBeanDefinition(ObjectX.class);
        objectXDef.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        applicationContext.registerBeanDefinition("objectX", objectXDef);
        //输出com.example.lcc.basic.spring.factory.Application$Person@4f51b3e0
        System.out.println(applicationContext.getBean("objectX", ObjectX.class).person);

        //输出com.example.lcc.basic.spring.factory.Application$Man@282003e1
        System.out.println(applicationContext.getBean("myFactoryBean"));
        //true 多次获取都是同一个对象
        System.out.println(applicationContext.getBean("myFactoryBean") == applicationContext.getBean("myFactoryBean"));
        //获取实际的工厂对象
        //输出com.example.lcc.basic.spring.factory.Application$MyFactoryBean@7fad8c79
        System.out.println(applicationContext.getBean("&myFactoryBean"));
    }

    @Bean
    public FactoryBean<Man> myFactoryBean(){
        return new MyFactoryBean();
    }

    public static class MyFactoryBean implements FactoryBean<Man> {

        @Override
        public Man getObject() throws Exception {
            return new Man();
        }

        @Override
        public Class<?> getObjectType() {
            return Man.class;
        }

        @Override
        public boolean isSingleton() {
            return true;
        }
    }

    @Component
    public static class ObjectX {
        @Autowired
        Person person;
    }
    public static class Person {

    }
    public static class Man {

    }

    public static class PersonObjectFactory implements ObjectFactory<Person> {

        @Override
        public Person getObject() throws BeansException {
            return new Person();
        }
    }


    @Test
    public void testForClassLoader() {
        //System.out.println("======>  " + Thread.currentThread().getContextClassLoader());

        ClassLoader cur = Thread.currentThread().getContextClassLoader();
        while (cur != null) {
            System.out.println("=======>  " + cur);
            cur = cur.getParent();
        }
    }

    @Bean
    public Student user() {
        Student student = new Student();
        student.setName("122222");
        student.setAge(18);
        return student;
    }

    <T> T getT(Class<T> tClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        T t = tClass.getDeclaredConstructor().newInstance();
        return t;
    }

    <T> T getTT(T t) {
        return t;
    }

    @SuppressWarnings(value = "unchecked")
    <T> T tTT() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> tClass = Class.forName("demo.spring.ioc.User");
        return (T) tClass.getDeclaredConstructor().newInstance();
    }
}
