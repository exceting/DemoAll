package demo.jdk.swing;

import javax.swing.*;

/**
 * Create by 18073 on 2019/11/20.
 */
public class HelloWorldSwing {

    public static void main(String[] args) {
        JFrame jf = new JFrame("Pleiades -singularity"); //创建一个JFrame对象
        jf.setSize(1500, 800);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 设置窗体关闭方式
        jf.setLocationRelativeTo(null);
        jf.setIconImage(new ImageIcon("./bilibili.png").getImage());
        jf.setVisible(true);  // 设置窗体可视
    }

}
