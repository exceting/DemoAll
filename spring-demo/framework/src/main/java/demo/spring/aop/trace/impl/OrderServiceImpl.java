package demo.spring.aop.trace.impl;

import demo.spring.aop.trace.OrderService;
import demo.spring.aop.trace.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public Order createOrder(String username, String product) {
        Order order = new Order();
        order.setUserName(username);
        order.setProduct(product);
        return order;
    }

    @Override
    public Order queryOrder(String username) {
        Order order = new Order();
        order.setUserName("test");
        order.setProduct("test");
        return order;
    }

    public static class XX {
    }

}
