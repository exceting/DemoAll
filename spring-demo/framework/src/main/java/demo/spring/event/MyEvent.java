package demo.spring.event;

import org.springframework.context.ApplicationEvent;

import java.util.EventObject;

public class MyEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public MyEvent(Object source) {
        super(source);
    }

    public void printTrigger() {
        System.out.println("收到MyEvent事件！");
    }
}
