package io.exceting.jobs;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.exceting.api.WechatService;
import io.exceting.api.YoudaoService;
import io.exceting.common.Constants;
import io.exceting.model.AddDraft;
import io.exceting.model.MediaUpload;
import io.exceting.model.QuoraAnswer;
import io.exceting.processors.InitProcessor;
import io.exceting.processors.ImageProcessor;
import io.exceting.processors.QuoraProcessor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class QuoraMainJob {

    // 任务描述：将Quora上的某篇回答翻译后上传公众号
    public static void main(String[] args) throws Exception {

        // 检查&初始化access_token
        InitProcessor.INSTANT.attachAccessToken("/Users/sunqinwen/Downloads/wechatAppInfo.txt",
                "/Users/sunqinwen/Downloads/wechatToken.txt");

        // 初始化有道appkey
        InitProcessor.INSTANT.attachYoudaoKey("/Users/sunqinwen/Downloads/youdaoKey.txt");

        String title = "外国人来中国后怎么看中国？";
        QuoraAnswer answer = QuoraProcessor.INSTANT.getAnswerByUrl("Have-foreigners-changed-the-way-they-think-about-China-after-visiting", "Nazim-Mehboob");

        List<String> images = Lists.newArrayList();
        answer.getSections().stream().filter(s -> s.getType().equals("image")).forEach(s -> s.getSpans().forEach(span -> images.add(span.getModifiers().getImage())));

        Map<String, String> quoraToWechatMap = Maps.newHashMap();
        List<String> imageMediaIds = Lists.newArrayList();
        if (images.size() > 0) {
            for (String image : images) {
                MediaUpload upload = ImageProcessor.INSTANT.mediaUpload(String.format("%s.jpg", UUID.randomUUID()), ImageProcessor.INSTANT.getImageByUrl(image));
                quoraToWechatMap.put(image, upload.getUrl());
                imageMediaIds.add(upload.getMediaId());
                System.out.printf("图片：%s  上传成功！mediaID = %s, url = %s%n", image, upload.getMediaId(), upload.getUrl());
            }
        }

        System.out.println("图片全部上传完成！现在开始生成文本");

        StringBuilder sb = new StringBuilder();
        for (QuoraAnswer.Section section : answer.getSections()) {
            if (section.getType().equals("image")) {
                for (QuoraAnswer.Section.Span span : section.getSpans()) {
                    sb.append(String.format("<br/><img src = '%s'/><br/>", quoraToWechatMap.get(span.getModifiers().getImage())));
                }
            } else {
                for (QuoraAnswer.Section.Span span : section.getSpans()) {
                    if (!Strings.isNullOrEmpty(span.getText())) {
                        String chinese = YoudaoService.INSTANT.translate(span.getText()).getTranslation();
                        chinese = chinese.substring(2, chinese.length() - 2);
                        if (span.getModifiers().isItalic()) {
                            sb.append(String.format("<p><em>%s</em></p>", chinese));
                        } else {
                            sb.append(String.format("<p>%s</p>", chinese));
                        }
                    }
                    System.out.println("完成一个段落！");
                }
            }
        }

        System.out.println("翻译完成！body = " + sb);

        String coverId = "3WbXOOIUbcDLYybxoF7K9veKTuqVR-6KbuKMeJdvujYVjoxAoBdFW9BMg4cn8rNr";
        AddDraft draft = WechatService.INSTANT.addDraft(Constants.VAL_MAP.get(Constants.VAL_MAP_ACCESS_TOKEN), title, "Java进化论", sb.toString(), coverId);

        System.out.println("上传成功！草稿mediaId=" + draft.getMediaId());
    }


}
