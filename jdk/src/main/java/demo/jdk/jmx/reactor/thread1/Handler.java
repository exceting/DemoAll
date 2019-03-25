/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.jmx.reactor.thread1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author sunqinwen
 * @version \: Handler.java,v 0.1 2019-03-25 16:48
 */
public class Handler implements Runnable {

    final SocketChannel channel;
    final SelectionKey sk;
    ByteBuffer input = ByteBuffer.allocate(1024);
    ByteBuffer output = ByteBuffer.allocate(1024);
    static final int READING = 0, SENDING = 1;
    int state = READING;

    Handler(Selector selector, SocketChannel c) throws IOException {
        channel = c;
        c.configureBlocking(false);
        // Optionally try first read now
        sk = channel.register(selector, 0);

        //将Handler作为callback对象
        sk.attach(this);

        //第二步,注册Read就绪事件
        sk.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }

    boolean inputIsComplete() {
        /* ... */
        return false;
    }

    boolean outputIsComplete() {

        /* ... */
        return false;
    }

    void process() {
        try {
            System.out.println(String.format("收到来自 %s 的消息: %s",
                    channel.getRemoteAddress(),
                    new String(input.array())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    public void run() {
        try {
            if (state == READING) {
                read();
            } else if (state == SENDING) {
                send();
            }
        } catch (IOException ex) { /* ... */ }
    }

    void read() throws IOException {
        int count = channel.read(input);
        if (count > 0) {

            process();

            state = SENDING;
            // Normally also do first write now

            //第三步,接收write就绪事件
            sk.interestOps(SelectionKey.OP_WRITE);
        } else {
            sk.cancel();
            channel.close();
            System.out.println("-------连接关闭");
        }
    }

    void send() throws IOException {
        output.clear();
        output.put("I get the message".getBytes());
        channel.write(output);

        //write完就结束了, 关闭select key
        if (outputIsComplete()) {
            sk.cancel();
        }
    }
}
