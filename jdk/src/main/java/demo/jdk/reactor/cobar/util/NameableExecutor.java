/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.reactor.cobar.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author sunqinwen
 * @version \: NameableExecutor.java,v 0.1 2019-04-04 11:16 
 *
 */
public class NameableExecutor extends ThreadPoolExecutor {

    protected String name;

    public NameableExecutor(String name, int size, BlockingQueue<Runnable> queue, ThreadFactory factory) {
        super(size, size, Long.MAX_VALUE, TimeUnit.NANOSECONDS, queue, factory);
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
