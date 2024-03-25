package demo.spring.service.impl;

import demo.spring.service.CalService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

//@Profile("j8")
@Service
public class J8CalService implements CalService {
    @Override
    public Integer sum(Integer... values) {
        return Stream.of(values).reduce(0, Integer::sum);
    }
}
