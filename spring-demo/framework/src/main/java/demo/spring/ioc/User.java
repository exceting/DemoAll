package demo.spring.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
//@Lazy
//@Scope("prototype")
public class User implements ApplicationContextAware, InitializingBean {

    public interface AAAAA {
    }

    public interface BBBBB {
    }

    public interface CCCCC extends AAAAA, BBBBB {
    }

    @Value(value = "11111")
    private long id;
    private String name;

    @Resource(name = "address1")
    //@Qualifier("address1")
    private Address address;

    @Autowired
    public void initAddress(@Qualifier("address1") Address address, Address address1) {
        System.out.println("=========== this.address1 = " + this.address + "      address1 = " + address + "     " + (this.address == address));
    }

    @Autowired
    private BeanFactory beanFactory;

    private int a = 5;

    public User() {
        System.out.println("User init... " + toString());
    }

    @PostConstruct
    public void init() {
        System.out.println(">>>>>>>>>>> User 自定义初始化方法触发！");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        System.out.println("--------- user.address seted");
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", a=" + a +
                ", address=" + address +
                ", address hash code=" + (address == null ? -1 : address.hashCode()) +
                '}';
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.a = 666;
        System.out.println("Application Context = " + applicationContext.getId() + "     address1 = " + this.address + "    id = " + this.id);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("************ a = " + this.a + "     " + toString());
        this.a = 777;
    }


    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
