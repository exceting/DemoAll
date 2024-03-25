package demo.spring.aop.transaction;

import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class TransactionService {

    @Transactional
    public void test() {
    }

}
