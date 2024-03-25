package demo.spring.aop.trace;

import demo.spring.aop.trace.model.Order;

public interface OrderService {

    Order createOrder(String username, String product);

    Order queryOrder(String username);

}
