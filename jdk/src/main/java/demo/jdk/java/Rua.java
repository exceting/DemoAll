/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.java;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.lang.reflect.Type;

/**
 * @author sunqinwen
 * @version \: Rua.java,v 0.1 2019-04-16 16:16
 */
public class Rua {

    private static final Type POJO_TYPE = new TypeReference<CanalResponse<Pojo>>() {
    }.getType();

    public static void main(String[] args) {
        String json = "{\"action\":\"update\",\"table\":\"test\",\"old\":{\"name\":\"0\"},\"new\":{\"name\":\"1\"}}";
        JSONObject jo = JSON.parseObject(json);
        System.out.println(jo.getString("table"));
        CanalResponse response = JSON.parseObject(json, POJO_TYPE);
        if (response.getNewx() instanceof Pojo) {
            System.out.println("~~~~~~");
        }
        System.out.println("sdsfdsdf");
    }

}
