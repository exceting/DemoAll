package demo.spring.circle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 构造器循环依赖问题验证
 */
@Component
public class Circle {

    @Autowired
    private Circle2 circle2;

    //public Circle() {
    //}

    @Autowired
    public Circle(Circle2 circle2) {
        this.circle2 = circle2;
    }

}
