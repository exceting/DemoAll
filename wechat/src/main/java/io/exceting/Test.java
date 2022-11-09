package io.exceting;

import io.exceting.processors.ImageProcessor;
import io.exceting.processors.QuoraProcessor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Test {

    public static void main(String[] args) throws Exception {
        BufferedImage bi = new BufferedImage(700, 150, BufferedImage.TYPE_INT_RGB);

        //得到绘制坏境(这张图片的笔)
        Graphics2D gh = bi.createGraphics();

        gh.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        gh.setColor(Color.WHITE); //设置颜色

        gh.fillRect(0, 0, 700, 150);//填充整张图片(其实就是设置背景色)

        gh.setStroke(new BasicStroke(2f));
        gh.setColor(new Color(0, 161, 214));
        gh.drawRect(1, 1, 690, 140);//绘制一个四边形边框
        gh.setColor(new Color(0, 161, 214, 139));
        gh.drawRect(5, 5, 690, 140);//绘制一个四边形边框
        gh.setColor(new Color(0, 161, 214, 66));
        gh.drawRect(9, 9, 690, 140);//绘制一个四边形边框

        //gh.setColor(Color.BLUE);//设置字体颜色
        //
        //

        BufferedImage headImage = ImageIO.read(new URL("https://qph.cf2.quoracdn.net/main-thumb-14375812-50-h6jpm6BHaVF1OwkLDPTYOEHCzOKMLRUk.jpeg"));
        headImage = convertCircular(headImage);

        gh.drawImage(headImage, 48, 32, 50, 50, null);

        gh.setColor(new Color(23, 23, 23));
        gh.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        gh.drawString("作者：Nazim Mehboob", 115, 50);//向图片上写随机字符串
        gh.drawString("居住：莫斯科（俄罗斯🇷🇺）", 115, 75);//向图片上写随机字符串

        gh.setColor(new Color(110, 110, 110));
        gh.drawOval(48, 32, 50, 50);
        gh.setColor(new Color(110, 110, 110, 150));
        gh.drawOval(45, 29, 56, 56);
        gh.setColor(new Color(110, 110, 110, 100));
        gh.drawOval(42, 26, 62, 62);
        gh.setColor(new Color(110, 110, 110, 50));
        gh.drawOval(39, 23, 68, 68);
        gh.setColor(new Color(110, 110, 110, 15));
        gh.drawOval(36, 20, 74, 74);

        ImageIO.write(bi, "PNG", Files.newOutputStream(Paths.get("/Users/sunqinwen/Downloads/test.png")));//把图片输出到指定路径
    }

    // 让图片变成圆形
    public static BufferedImage convertCircular(BufferedImage bi1) throws IOException {
        System.out.println("w=" + bi1.getWidth() + "   h=" + bi1.getHeight());
        BufferedImage bi2 = new BufferedImage(bi1.getWidth(), bi1.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, bi1.getWidth(), bi1.getHeight());
        Graphics2D gh = bi2.createGraphics();
        gh.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gh.setClip(shape);
        gh.drawImage(bi1, 0, 0, null);
        // 设置颜色
        gh.setBackground(Color.green);
        gh.dispose();
        return bi2;
    }

}
