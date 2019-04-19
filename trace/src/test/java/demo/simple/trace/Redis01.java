package demo.simple.trace;

/**
 * Create by 18073 on 2019/4/18.
 */
public class Redis01 {

    //模拟redis服务
    String get(String key){
        return String.format("key=%s, value=%s", key, "rua");
    }

}
