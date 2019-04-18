/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.java;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *
 * @author sunqinwen
 * @version \: CanalResponse.java,v 0.1 2019-04-16 16:16 
 *
 */
public class CanalResponse<T> {
    @JSONField(name = "new")
    private T newx;
    private T old;
    private String action;
    private String table;


    public T getNewx() {
        return newx;
    }

    public void setNewx(T newx) {
        this.newx = newx;
    }

    public T getOld() {
        return old;
    }

    public void setOld(T old) {
        this.old = old;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
}