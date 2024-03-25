package demo.spring.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//@Component
//@PropertySource("classpath:student.properties")
public class Student {

    private String name;
    private int age;

    @Bean(name = "userInStudent")
    public User userInStudent() {
        User user = new User();
        user.setId(5556666);
        user.setName("userInStudent");
       // user.setAddress(address2);
        return user;
    }

    @PostConstruct
    public void init() {
        System.out.println(">>>>>>>>> Student 自定义初始化方法触发！");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hash code=" + this.hashCode() +
                '}';
    }
}
