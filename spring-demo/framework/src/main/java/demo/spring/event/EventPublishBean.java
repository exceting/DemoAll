package demo.spring.event;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventPublishBean implements InitializingBean, ApplicationEventPublisherAware {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher; // 其实就是ApplicationContext上下文对象

    private ApplicationEventPublisher applicationEventPublisher2;

    @Autowired
    private ApplicationEventMulticaster applicationEventMulticaster;

    @EventListener(classes = MyEvent.class)
    public void event() {
        System.out.println("EventPublishBean 收到MyEvent事件！");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(">>>>>  " + (applicationEventPublisher == applicationEventPublisher2) + "    " + applicationEventPublisher.hashCode());
        applicationEventPublisher.publishEvent(new MyEvent(this));
        //applicationEventMulticaster.multicastEvent(new MyEvent(this));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher2 = applicationEventPublisher;
    }
}
