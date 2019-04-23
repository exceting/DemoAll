/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.reactor.client;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 *
 * @author sunqinwen
 * @version \: Connector.java,v 0.1 2019-03-29 12:29 
 *
 */
public class Connector implements Runnable {

    private final Selector selector;

    private final SocketChannel socketChannel;

    Connector(SocketChannel socketChannel, Selector selector) {
        this.socketChannel = socketChannel;
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            if (socketChannel.finishConnect()) { //这里连接完成（与服务端的三次握手完成）
                System.out.println(String.format("已完成 %s 的连接",
                        socketChannel.getRemoteAddress()));
                new Handler(socketChannel, selector); //连接建立完成后，接下来的动作交给Handler去处理（读写等）
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
