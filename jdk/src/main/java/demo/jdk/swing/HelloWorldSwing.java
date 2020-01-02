package demo.jdk.swing;

import javax.swing.*;
import java.awt.*;

/**
 * Create by 18073 on 2019/11/20.
 */
public class HelloWorldSwing {

    public static void main(String[] args) {
        JFrame jf = new JFrame("Pleiades Singularity"); //创建一个JFrame对象
        jf.setSize(1500, 800);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 设置窗体关闭方式
        jf.setLocationRelativeTo(null);
        jf.setIconImage(new ImageIcon("bilibili.png").getImage());
        jf.setVisible(true);  // 设置窗体可视

        Container c = jf.getContentPane();

        JPanel jp = new JPanel();
        jp.setBackground(new Color(54, 54, 54));
        JLabel title = new JLabel("欢迎使用Singularity");
        jp.add(title);

        JLabel title2 = new JLabel("万物之始");
        jp.add(title2);

        JLabel image = new JLabel(new ImageIcon("bilibili.png"));
        jp.add(image);

        c.add(jp);
    }

}
