package demo.spring.aop.trace;

import demo.spring.aop.trace.model.User;

public interface UserService {

    User createUser(String firstName, String lastName, int age);

    User queryUser();

}
