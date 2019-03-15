/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.concurrent.async.future;

/**
 * @author sunqinwen
 * @version \: WebModule.java,v 0.1 2019-01-16 18:56
 */
public class WebModule {

    private String top; //顶部Banner位

    private String left; //左边栏

    private String right; //右边栏

    private String user; //用户信息

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return String.format("top: %s;  left: %s;  right: %s;  user: %s", top, left, right, user);
    }
}
