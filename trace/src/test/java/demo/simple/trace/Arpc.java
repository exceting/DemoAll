package demo.simple.trace;

import com.google.common.collect.Maps;
import demo.simple.context.ContextHolder;

import java.util.Map;

/**
 * Create by 18073 on 2019/4/18.
 */
public class Arpc {

    private Redis01 redis01 = new Redis01();

    private Map<String, String> headers = Maps.newConcurrentMap();

    //这里对应实际项目中rpc服务触发调用前的执行操作，对应拦截器中的before，请求开始的地方，实际项目中若没有需要自定义
    void beforeRpc() {
        //查看本次请求报头是否有约定的链路id，若有，表示本次调用属于别的系统的子链路
        String tracrId = headers.get("trace-id");
        Long parentId = tracrId == null ? null : Long.parseLong(tracrId);
        if (parentId != null && parentId > 0) {
            String sample = headers.get("");
        }
    }

    //这里对应实际项目中rpc服务触发调用后的执行操作，对应拦截器中的after
    void afterRpc() {

    }

    //模拟A服务
    String getA(String key) {
        beforeRpc();
        String result = reqRedis(key);
        afterRpc();
        return result;
    }

    //这里理解成实际项目中A服务通过redis客户端调用Redis01服务
    String reqRedis(String key) {
        return redis01.get(key);
    }

}
