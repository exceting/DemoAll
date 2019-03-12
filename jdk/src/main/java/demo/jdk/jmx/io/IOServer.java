package demo.jdk.jmx.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Create by 18073 on 2019/3/12.
 */
public class IOServer {

    public static void main(String[] args) throws IOException {

        try {
            ServerSocket serverSocket = new ServerSocket(2333);
            byte[] recvBuf = new byte[1024];
            while (true) {

                int recvMsgSize = 0;
                InputStream in = null;
                try {
                    Socket clntSocket = serverSocket.accept();
                    SocketAddress clientAddress = clntSocket.getRemoteSocketAddress();
                    System.out.println("连接成功，处理客户端：" + clientAddress);
                    in = clntSocket.getInputStream();
                    while ((recvMsgSize = in.read(recvBuf)) != -1) {
                        byte[] temp = new byte[recvMsgSize];
                        System.arraycopy(recvBuf, 0, temp, 0, recvMsgSize);
                        System.out.println("收到客户端" + clientAddress + "的消息内容：" + new String(temp));
                    }
                    System.out.println("-------------------------");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (serverSocket != null) {
                            System.out.println("socket关闭！");
                            serverSocket.close();
                        }
                        if (in != null) {
                            System.out.println("stream连接关闭！");
                            in.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
