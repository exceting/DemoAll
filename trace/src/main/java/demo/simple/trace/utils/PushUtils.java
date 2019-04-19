/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2018 All Rights Reserved.
 */

package demo.simple.trace.utils;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Random;

public class PushUtils {

    public static final Random random = new Random();

    private static final Map<String, Long> requestMap = Maps.newConcurrentMap();

    /**
     * 上报概率计算，
     * @param uri
     * @return
     */
    public static boolean sampled(String uri) {

        if (Strings.isNullOrEmpty(uri)) {
            return false;
        }

        Long start = requestMap.get(uri);
        Long end = System.currentTimeMillis();
        if (start == null) {
            requestMap.put(uri, end);
            return true;
        }
        if ((end - start) >= 60000) { //距离上次上报已经超过1min了
            requestMap.put(uri, end);
            return true;
        } else { // 没超过5min，则按照1/2233的概率上报
            if (random.nextInt(2232) == 0) {
                requestMap.put(uri, end);
                return true;
            }
        }

        return false;
    }

}
