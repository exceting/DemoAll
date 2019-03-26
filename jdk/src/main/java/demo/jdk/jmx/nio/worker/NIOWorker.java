/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.jmx.nio.worker;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author sunqinwen
 * @version \: NIOWorker.java,v 0.1 2019-03-13 17:51
 */
public class NIOWorker {

    private static final ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    public void done(int count, ByteBuffer buffer, SocketChannel socketChannel, SelectionKey selectionKey) {
        service.submit(() -> {

            int rn = (int) (Math.random() * 5000);
            System.out.println("----" + rn);
            Thread.sleep(rn);

            if (count == -1) {
                System.out.println("count = " + count);
            }
            /*if (count == 0) {
                return null;
            }*/
            // if (count > 0) {
            System.out.println(String.format("收到来自 %s 的消息: %s",
                    socketChannel.getRemoteAddress(),
                    new String(buffer.array())));
            //}
            return null;
        });
    }

}
