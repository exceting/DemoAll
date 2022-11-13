package io.exceting.processors;

import com.alibaba.fastjson.JSONObject;
import io.exceting.api.QuoraService;
import io.exceting.common.Constants;
import io.exceting.model.QuoraAnswer;
import org.apache.commons.text.StringEscapeUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

public class QuoraProcessor {

    public static final QuoraProcessor INSTANT = new QuoraProcessor();

    private QuoraProcessor() {
    }

    public QuoraAnswer getAnswerByUrl(String qid, String aid) throws Exception {
        String body = QuoraService.INSTANT.getAnswer(qid, aid);
        body = body.substring(body.indexOf("{\\\"data\\\":{\\\"answer"));
        body = StringEscapeUtils.unescapeJava(body.substring(0, body.indexOf("}}\");") + 2));

        JSONObject jsonObject = JSONObject.parseObject(body);
        return JSONObject.parseObject(jsonObject.getJSONObject("data").getJSONObject("answer").getJSONObject("content").toJSONString(), QuoraAnswer.class);
    }

    // 生成版头
    public byte[] getHeaderImg(String userName,
                               String avatarUrl,
                               String country,
                               String countryFlagUrl,
                               String views,
                               String likes,
                               String rank) throws Exception {
        BufferedImage bi = new BufferedImage(701, 240, BufferedImage.TYPE_INT_ARGB);

        //得到绘制坏境(这张图片的笔)
        Graphics2D gh = bi.createGraphics();

        gh.setColor(Color.WHITE); //设置颜色

        gh.fillRect(0, 0, 701, 240);

        gh.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        BufferedImage headImage = ImageIO.read(new URL(Constants.QUORA_HEADER_BG));
        gh.drawImage(headImage, 0, 0, 701, 240, null);

        BufferedImage avatar = ImageIO.read(new URL(avatarUrl));
        avatar = convertCircular(avatar);

        gh.drawImage(avatar, 312, 9, 75, 75, null);

        gh.setStroke(new BasicStroke(2f));
        gh.setColor(new Color(250, 238, 182));
        gh.drawOval(312, 9, 75, 75);

        // 坐标
        BufferedImage flagIcon = ImageIO.read(new URL(countryFlagUrl));
        gh.drawImage(flagIcon, 125, 102, 28, 28, null);
        gh.setFont(new Font("微软雅黑", Font.ITALIC, 12));
        gh.setColor(new Color(136, 136, 136));
        gh.drawString(String.format("（%s）", country), 150, 122);

        // 答主
        gh.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        gh.setColor(new Color(3, 84, 110));
        gh.drawString(userName, 360, 122);

        // 浏览
        gh.setFont(new Font("微软雅黑", Font.BOLD, 18));
        gh.setColor(new Color(0, 194, 187));
        gh.drawString(views, 125, 162);

        // 获赞
        gh.drawString(likes, 360, 162);

        // 获赞
        gh.drawString(rank, 595, 162);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bi, "png", os);
        return os.toByteArray();
    }

    // 让图片变成圆形
    public BufferedImage convertCircular(BufferedImage bi1) throws IOException {
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
