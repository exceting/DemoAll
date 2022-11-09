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

        gh.setColor(Color.WHITE); //设置颜色

        gh.fillRect(0, 0, 700, 150);

        //gh.setStroke(new BasicStroke(2f));
        gh.setColor(new Color(251, 114, 153));
        gh.drawRect(6, 6, 690, 140);
        gh.setColor(new Color(0, 161, 214));
        gh.drawRect(1, 1, 690, 140);

        gh.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        BufferedImage headImage = ImageIO.read(new URL("http://mmbiz.qpic.cn/mmbiz_png/YBlrUyYcnjTZ3cC7FxHJdDiaYoABsiciaHiakibw1YBxj58t9ZvteaOUyt2PuIJrWuHVmXJubdp0K9JiaE4EKLcJYjuw/0"));
        headImage = convertCircular(headImage);

        gh.drawImage(headImage, 48, 32, 50, 50, null);

        gh.setColor(new Color(34, 34, 34));

        BufferedImage userIcon = ImageIO.read(new URL("https://myblog.sharemer.com/wechat/icons/user.png"));
        gh.drawImage(userIcon, 115, 35, 20, 20, null);
        gh.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        gh.drawString("作者：Nazim Mehboob", 140, 50);

        BufferedImage zbIcon = ImageIO.read(new URL("https://myblog.sharemer.com/wechat/icons/marker3.png"));
        gh.drawImage(zbIcon, 115, 63, 20, 20, null);

        gh.drawString("坐标：", 140, 80);
        BufferedImage flagIcon = ImageIO.read(new URL("https://img.icons8.com/color/2x/finland.png"));
        gh.drawImage(flagIcon, 185, 61, 28, 28, null);
        gh.setFont(new Font("微软雅黑", Font.ITALIC, 12));
        gh.setColor(new Color(136, 136, 136));
        gh.drawString("（芬兰）", 210, 80);

        gh.setColor(new Color(172, 220, 15));
        gh.drawOval(48, 32, 50, 50);
        gh.setColor(new Color(172, 220, 15, 150));
        gh.drawOval(45, 29, 56, 56);
        gh.setColor(new Color(172, 220, 15, 100));
        gh.drawOval(42, 26, 62, 62);
        gh.setColor(new Color(172, 220, 15, 50));
        gh.drawOval(39, 23, 68, 68);
        gh.setColor(new Color(172, 220, 15, 15));
        gh.drawOval(36, 20, 74, 74);

        gh.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        gh.setColor(new Color(56, 56, 56));

        gh.drawString("Quora指标：", 210, 80);

        ImageIO.write(bi, "PNG", Files.newOutputStream(Paths.get("D:/test.png")));//把图片输出到指定路径
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
