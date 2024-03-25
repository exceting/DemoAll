package demo.spring.listeners;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class MyApplicationListener implements SpringApplicationRunListener {

    public MyApplicationListener(SpringApplication application, String[] args) {

    }

    @Override
    public void starting() {
        System.out.println("ccccccc------> starting");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        System.out.println("ccccccc------> environmentPrepared");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("ccccccc------> contextPrepared");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("ccccccc------> contextLoaded");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        System.out.println("ccccccc------> started");
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        System.out.println("ccccccc------> running");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println("ccccccc------> failed");
    }
}
