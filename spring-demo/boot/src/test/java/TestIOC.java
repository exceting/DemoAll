import demo.spring.bootstrap.CalServiceBootstrap;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestIOC {

    @Test
    public void test(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CalServiceBootstrap.class);
        
    }

}
