/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.websocket.po;

import java.util.Date;

/**
 * @author sunqinwen
 * @version \: User.java,v 0.1 2019-03-01 15:15
 */

public class User {

    private int id;

    private String name;

    private String password;

    private Date birth;

    private String address;

    private String avater;

    private int is_married;

    private Date create_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public int getIs_married() {
        return is_married;
    }

    public void setIs_married(int is_married) {
        this.is_married = is_married;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
