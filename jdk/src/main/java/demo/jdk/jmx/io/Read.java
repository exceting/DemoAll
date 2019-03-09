package demo.jdk.jmx.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Create by 18073 on 2019/3/4.
 */
public class Read {

    public static void main(String[] args) {
        Read.writeFile();
    }

    public static void readFile() {
        RandomAccessFile readFile = null;
        try {
            readFile = new RandomAccessFile("D:\\rua.txt", "r");
            FileChannel readChannel = readFile.getChannel();
            ByteBuffer first = ByteBuffer.allocate(2); //第一块buffer
            ByteBuffer second = ByteBuffer.allocate(2); //第二块buffer
            ByteBuffer[] byteBuffers = {first, second};
            long bytesRead = readChannel.read(byteBuffers); //从通道里读取数据到Buffer内（最大不超过Buffer容积）
            while (bytesRead != -1) { //当读不到任何东西时返回-1
                System.out.println("\nheader里的数据------此时byteRead=" + bytesRead);
                first.flip(); //切换到Buffer读模式，读模式下可以读取到之前写入Buffer的数据
                while (first.hasRemaining()) {
                    System.out.print("-" + (char) first.get()); //第一块Buffer读出的数据用减号分割，用于跟第二块区分
                }
                first.clear();
                System.out.println("\nbody里的数据------此时byteRead=" + bytesRead);
                second.flip();
                while (second.hasRemaining()) {
                    System.out.print("+" + (char) second.get()); //第二块Buffer读出的数据用加号分割，用于跟第一块区分
                }
                second.clear(); // 切换回Buffer的写模式
                System.out.println("\n----------------------------------------------");
                bytesRead = readChannel.read(byteBuffers); //跟上面一样，再次从通道读取数据到Buffer中
            }
            System.out.print("\n-----------程序结束");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (readFile != null) {
                    readFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeFile() {
        RandomAccessFile readFile = null;
        try {
            readFile = new RandomAccessFile("D:\\haha.txt", "rw");
            FileChannel channel = readFile.getChannel();
            ByteBuffer first = ByteBuffer.allocate(5); //第一块buffer
            ByteBuffer second = ByteBuffer.allocate(5); //第二块buffer
            first.put("aa".getBytes());
            second.put("bb".getBytes());
            first.flip();
            second.flip();
            ByteBuffer[] byteBuffers = {second, first};
            channel.write(byteBuffers);
            System.out.print("\n-----------程序结束");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (readFile != null) {
                    readFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readFile2() {
        RandomAccessFile readFile = null;
        try {
            readFile = new RandomAccessFile("D:\\rua.txt", "r");
            FileChannel readChannel = readFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(2); //第一块buffer
            int bytesRead = readChannel.read(buf); //从通道里读取数据到Buffer内（最大不超过Buffer容积）
            while (bytesRead != -1) { //当读不到任何东西时返回-1
                buf.flip();
                System.out.print((char) buf.get());
                buf.rewind(); //重置position
                buf.clear();
                bytesRead = readChannel.read(buf);
            }
            System.out.print("\n-----------程序结束");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (readFile != null) {
                    readFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void method2() {
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream("D:\\rua.txt"));
            byte[] buf = new byte[1];
            int bytesRead = in.read(buf);
            while (bytesRead != -1) {
                for (int i = 0; i < bytesRead; i++)
                    System.out.print((char) buf[i]);
                bytesRead = in.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
