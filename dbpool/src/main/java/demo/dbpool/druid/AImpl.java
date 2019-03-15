/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.dbpool.druid;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author sunqinwen
 * @version \: AImpl.java,v 0.1 2019-02-11 15:38 
 *
 */
public class AImpl implements A {

    private ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    @Override
    public void one() {
        Map<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        System.out.println(map.get("1"));

        Map<String, Integer> map2 = new ConcurrentHashMap<>();
        map2.put("1", 1);
        System.out.println(map2.get("1"));

        threadLocal.set(1);

        System.out.println("---------"+threadLocal.get());
    }
}
