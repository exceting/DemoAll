/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.jmx.nio.worker;

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
 * @version \: AsyncNIOServer.java,v 0.1 2019-03-13 17:55
 */
public class AsyncNIOServer {

    public static void main(String[] args) {
        Selector selector = null;
        ServerSocketChannel serverSocketChannel = null;
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(2333));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                int selected = selector.select();
                if (selected <= 0) {
                    System.out.println("ERROR! selected <= 0  value = " + selected);
                    continue;
                }
                //System.out.println("~~~~~~selected = " + selected);
                Set<SelectionKey> keys = selector.selectedKeys();
                //System.out.println("~~~~~~~~~~key size = " + (keys.size()));
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (key.isValid() && key.isAcceptable()) {
                        ServerSocketChannel skc = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = skc.accept();
                        socketChannel.configureBlocking(false);
                        System.out.println(String.format("收到来自 %s 的连接", socketChannel.getRemoteAddress()));
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isValid() && key.isReadable()) {
                        //System.out.println("----------------------------读模式");
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        int count = socketChannel.read(buffer);
                        if (count < 0) {
                            //socketChannel.close();
                            key.cancel();
                            System.out.println("连接关闭");
                        } else {
                            NIOWorker worker = new NIOWorker();
                            worker.done(count, buffer, socketChannel, key);
                        }
                    }
                }
                //keys.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocketChannel != null) {
                    serverSocketChannel.close();
                }
                if (selector != null) {
                    selector.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
