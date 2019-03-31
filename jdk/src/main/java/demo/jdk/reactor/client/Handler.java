/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.reactor.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sunqinwen
 * @version \: Handler.java,v 0.1 2019-03-29 12:17
 */
public class Handler implements Runnable {

    private final SelectionKey selectionKey;
    private final SocketChannel socketChannel;

    private ByteBuffer readBuffer = ByteBuffer.allocate(2048);
    private ByteBuffer sendBuffer = ByteBuffer.allocate(1024);

    private final static int READ = 0;
    private final static int SEND = 1;

    private int status = SEND;

    private AtomicInteger counter = new AtomicInteger();

    Handler(SocketChannel socketChannel, Selector selector) throws IOException {
        this.socketChannel = socketChannel; //接收客户端连接
        this.socketChannel.configureBlocking(false); //置为非阻塞模式（selector仅允非阻塞模式）
        selectionKey = socketChannel.register(selector, 0); //将该客户端注册到selector，得到一个SelectionKey，以后的select到的就绪动作全都是由该对象进行封装
        selectionKey.attach(this); //附加处理对象，当前是Handler对象，run是对象处理业务的方法
        selectionKey.interestOps(SelectionKey.OP_WRITE); //走到这里，说明之前Acceptor里的建连已完成，那么接下来就是读取动作，因此这里首先将读事件标记为“感兴趣”事件
        selector.wakeup(); //唤起select阻塞
    }

    @Override
    public void run() {
        try {
            switch (status) {
                case SEND:
                    send();
                    break;
                case READ:
                    read();
                    break;
                default:
            }
        } catch (IOException e) { //这里的异常处理是做了汇总，同样的，客户端也面临着正在与服务端进行写/读数据时，突然因为网络等原因，服务端直接断掉连接，这个时候客户端需要关闭自己并退出程序
            System.err.println("send或read时发生异常！异常信息：" + e.getMessage());
            selectionKey.cancel();
            try {
                socketChannel.close();
            } catch (IOException e2) {
                System.err.println("关闭通道时发生异常！异常信息：" + e2.getMessage());
                e2.printStackTrace();
            }
        }
    }

    void send() throws IOException {
        if (selectionKey.isValid()) {
            sendBuffer.clear();
            int count = counter.incrementAndGet();
            if (count <= 10) {
                sendBuffer.put(String.format("客户端发送的第%s条消息", count).getBytes());
                sendBuffer.flip(); //切换到读模式，用于让通道读到buffer里的数据
                socketChannel.write(sendBuffer);

                //则再次切换到读，用以接收服务端的响应
                status = READ;
                selectionKey.interestOps(SelectionKey.OP_READ);
            } else {
                selectionKey.cancel();
                socketChannel.close();
            }
        }
    }

    private void read() throws IOException {
        if (selectionKey.isValid()) {
            readBuffer.clear(); //切换成buffer的写模式，用于让通道将自己的内容写入到buffer里
            socketChannel.read(readBuffer);
            System.out.println(String.format("收到来自服务端的消息: %s", new String(readBuffer.array())));

            //收到服务端的响应后，再继续往服务端发送数据
            status = SEND;
            selectionKey.interestOps(SelectionKey.OP_WRITE); //注册写事件
        }
    }
}
