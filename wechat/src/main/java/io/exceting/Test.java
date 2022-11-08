package io.exceting;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Test {

    public static void main(String[] args) throws Exception {
        BufferedImage bi = new BufferedImage(700, 100, BufferedImage.TYPE_INT_RGB);

        //得到绘制坏境(这张图片的笔)
        Graphics gh = bi.getGraphics();

        gh.setColor(Color.WHITE); //设置颜色

        gh.fillRect(0, 0, 700, 100);//填充整张图片(其实就是设置背景色)

        gh.setColor(new Color(0, 161, 214));

        gh.drawRect(1, 1, 690, 90);//绘制一个四边形边框
        gh.drawRect(4, 4, 690, 90);//绘制一个四边形边框
        gh.drawRect(7, 7, 690, 90);//绘制一个四边形边框

        gh.setColor(Color.BLUE);//设置字体颜色
        gh.setFont(new Font("微软雅黑", Font.BOLD, 18));

        gh.drawString("你XX", 22, 25);//向图片上写随机字符串

        ImageIO.write(bi, "PNG", Files.newOutputStream(Paths.get("D:/test.png")));//把图片输出到指定路径
    }

}
