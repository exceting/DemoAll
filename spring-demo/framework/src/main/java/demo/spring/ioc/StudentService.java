package demo.spring.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Bean
    public String hhhhh() {
        return "hhhhh:)";
    }

}
