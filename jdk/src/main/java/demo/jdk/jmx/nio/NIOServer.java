/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.jmx.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author sunqinwen
 * @version \: NIOServer.java,v 0.1 2019-03-13 16:48
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {

        Selector selector = Selector.open(); //打开选择器

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(2333));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            Set<SelectionKey> keys = selector.selectedKeys();
            System.out.println("~~~~~~~~~~key size = " + (keys.size()));
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    ServerSocketChannel skc = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = skc.accept();
                    socketChannel.configureBlocking(false);
                    System.out.println(String.format("收到来自 %s 的连接", socketChannel.getRemoteAddress()));
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    //System.out.println("----------------------------读模式");
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int count = socketChannel.read(buffer);
                    if (count < 0) {
                        socketChannel.close();
                        key.cancel();
                        System.out.println("连接关闭");
                        continue;
                    }
                    System.out.println(String.format("收到来自 %s 的消息: %s",
                            socketChannel.getRemoteAddress(),
                            new String(buffer.array())));
                }
                keys.remove(key);
            }
        }
    }
}
