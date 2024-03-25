package demo.spring.aop.aspectj;

import org.springframework.stereotype.Service;

@Service
public class AopService {

    public void m1() {
        System.out.println("m1 trigger!");
        m2();
    }

    public void m2() {
        System.out.println("m2 trigger!");
    }

}
