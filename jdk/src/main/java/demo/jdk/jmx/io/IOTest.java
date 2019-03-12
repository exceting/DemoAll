package demo.jdk.jmx.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Create by 18073 on 2019/3/12.
 */
public class IOTest {

    private static ExecutorService ctp = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            ctp.submit(IOTest::client); //并发十个客户端连接过去
        }
    }

    public static void client() {
        ByteBuffer buffer = ByteBuffer.allocate(1024); //定义缓冲区
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open(); //打开SocketChannel
            socketChannel.configureBlocking(false); //设置为非阻塞模式
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 2333)); //连接服务
            while (true) {
                if(socketChannel.finishConnect()){ //这里的finishConnect是尝试连接，有可能返回false，因此使用死循环进行连接检查，确保连接已经正常建立。
                    System.out.println("客户端已连接到服务器");
                    int i = 0;
                    while (i < 5) {
                        TimeUnit.SECONDS.sleep(1); //隔一秒钟写一条
                        String info = "来自客户端的第" + (i++) + "条消息";
                        buffer.clear();
                        buffer.put(info.getBytes());
                        buffer.flip();
                        while (buffer.hasRemaining()) {
                            socketChannel.write(buffer); //给服务发消息
                        }
                    }
                    break;
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socketChannel != null) {
                    System.out.println("客户端Channel关闭");
                    socketChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
