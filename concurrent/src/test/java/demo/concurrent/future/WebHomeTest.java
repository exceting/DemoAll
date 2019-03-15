/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.concurrent.future;
import demo.concurrent.async.future.WebHome;
import demo.concurrent.async.future.WebModule;
import org.junit.Test;

/**
 * @author sunqinwen
 * @version \: WebHomeTest.java,v 0.1 2019-01-17 11:39
 */
public class WebHomeTest {

    private WebHome webHome = new WebHome();

    @Test
    public void testSync() {
        // 同步方法测试，预估耗时430ms
        long start = System.currentTimeMillis();
        WebModule module = webHome.getWebModuleMsgSync();
        System.out.println("通过同步方法获取首页全部信息消耗时间：" + (System.currentTimeMillis() - start) + "ms");
        System.out.println("结果为：" + module.toString());
    }

    @Test
    public void testASync() throws Exception {
        // 同步方法测试，预估耗时200ms
        long start = System.currentTimeMillis();
        WebModule module = webHome.getWebModuleMsgAsync();
        System.out.println("通过异步方法获取首页全部信息消耗时间：" + (System.currentTimeMillis() - start) + "ms");
        System.out.println("结果为：" + module.toString());
    }

}
