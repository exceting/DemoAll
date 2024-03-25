package demo.spring.ioc;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Address implements InitializingBean, DisposableBean {

    private int code;
    private String addr;

    public Address() {
        System.out.println("Address 实例化...");
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        System.out.println("set code... code = " + code);
        this.code = code;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "Address{" +
                "code=" + code +
                ", addr='" + addr + '\'' +
                '}';
    }

    public void destroy() {
        System.out.println("Address bean destroyed!");
    }

    public void afterPropertiesSet() {
        System.out.println("Address bean after property set!");
        //this.code  = 111111;
    }
}
