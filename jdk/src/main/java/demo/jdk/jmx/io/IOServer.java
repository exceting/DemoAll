package demo.jdk.jmx.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Create by 18073 on 2019/3/12.
 */
public class IOServer {

    private static ExecutorService ctp = Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(2333); //开一个监听2333端口的TCP服务
            byte[] recvBuf = new byte[1024];
            while (true) {
                Socket clntSocket = serverSocket.accept(); //探听有没有新的客户端连接进来，没有就阻塞
                SocketAddress clientAddress = clntSocket.getRemoteSocketAddress(); //通过跟服务连接上的客户端socket，拿到客户端地址
                System.out.println("连接成功，处理客户端：" + clientAddress);


                ctp.execute(() -> {
                    int recvMsgSize = 0;
                    InputStream in = null; //数据流
                    try {
                        in = clntSocket.getInputStream();
                        while ((recvMsgSize = in.read(recvBuf)) != -1) { //读取发送的数据，当客户端未断开连接，且不往服务端发数据的时候，说明一直处于准备读的状态，会一直阻塞下去，直到有数据写入（读就绪）
                            byte[] temp = new byte[recvMsgSize];
                            System.arraycopy(recvBuf, 0, temp, 0, recvMsgSize);
                            System.out.println("收到客户端" + clientAddress + "的消息内容：" + new String(temp)); //打印消息
                        }
                        System.out.println("-----------------------------------");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    System.out.println("socket关闭！");
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
