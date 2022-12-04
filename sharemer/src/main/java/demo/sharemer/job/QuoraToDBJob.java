package demo.sharemer.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import demo.sharemer.api.QiniuService;
import demo.sharemer.api.QuoraService;
import demo.sharemer.api.YoudaoService;
import demo.sharemer.config.AppProperties;
import demo.sharemer.mapper.quora.QuoraAnswerMapper;
import demo.sharemer.mapper.quora.QuoraQuestionMapper;
import demo.sharemer.mapper.quora.QuoraUserMapper;
import demo.sharemer.mapper.quora.WechatImageMapper;
import demo.sharemer.model.QuoraAnswer;
import demo.sharemer.model.QuoraQuestion;
import demo.sharemer.model.QuoraUser;
import demo.sharemer.model.WechatImage;
import demo.sharemer.model.http.GetAnswersRes;
import demo.sharemer.model.http.QuoraAnswerContent;
import demo.sharemer.service.ImageProcessor;
import demo.sharemer.service.QuoraProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;

// 负责quora落库
@Slf4j
@Component
public class QuoraToDBJob {

    @Resource
    private QuoraService quoraService;

    @Resource
    private YoudaoService youdaoService;

    @Resource
    private QuoraQuestionMapper quoraQuestionMapper;

    @Resource
    private QuoraProcessor quoraProcessor;

    @Resource
    private ImageProcessor imageProcessor;

    @Resource
    private QuoraAnswerMapper quoraAnswerMapper;

    @Resource
    private QuoraUserMapper quoraUserMapper;

    @Resource
    private WechatImageMapper wechatImageMapper;

    @Resource
    private QiniuService qiniuService;

    @Resource
    private AppProperties appProperties;

    public void process(String idx) throws Exception {
        long s = System.currentTimeMillis();
        process(idx, null, 5);
        int i = 4;
        while (true) {
            long ss = System.currentTimeMillis();
            boolean r = process(idx, i, 10);
            log.info("回答落库任务完成！cursor = {}, count = {}, 总耗时 = {}ms", i, 10, System.currentTimeMillis() - ss);
            i += 10;
            if (!r) {
                break;
            }
        }
        log.info("回答落库任务完成！总耗时 = {}ms", System.currentTimeMillis() - s);
    }

    public boolean process(String idx, Integer cursor, Integer count) throws Exception {
        List<GetAnswersRes.AnswerItem.Data> answers = quoraService.getAnswers(idx, cursor, count);
        if (answers != null && answers.size() > 0) {

            GetAnswersRes.AnswerItem.Data data = answers.get(0);
            processQ(data.getNode().getQuestion().getQid(),
                    data.getNode().getQuestion().getId(),
                    data.getNode().getAnswer().getQuestion().getUrl(),
                    data.getNode().getAnswer().getQuestion().getTitle()
            ); // 处理问题

            for (GetAnswersRes.AnswerItem.Data answer : answers) {
                long s = System.currentTimeMillis();
                processA(answer); // 处理回答
                log.info("完成一个回答的落库！！耗时 = {}ms", System.currentTimeMillis() - s);
            }
            return true;
        }
        return false;
    }

    private void processQ(Long qid, String id, String url, String title) throws Exception {
        QuoraQuestion q = new QuoraQuestion();
        q.setId(qid);
        q.setIdx(id);
        q.setUrl(url);
        q.setCtime(LocalDateTime.now());

        QuoraQuestion question = quoraQuestionMapper.one(q.getId());
        if (question == null) {
            if (!Strings.isNullOrEmpty(title)) {
                title = StringEscapeUtils.unescapeJava(title);
                QuoraAnswerContent answerContent = JSONObject.parseObject(title, QuoraAnswerContent.class);
                title = answerContent.getSections().get(0).getSpans().get(0).getText();
                q.setTitleOri(title);
                q.setTitleCn(youdaoService.translate(q.getTitleOri()));
            } else {
                throw new IllegalStateException("问题标题为空！");
            }
            quoraQuestionMapper.insert(q);
        } else {
            quoraQuestionMapper.update(q);
        }
    }

    // 处理回答
    private void processA(GetAnswersRes.AnswerItem.Data answer) throws Exception {

        if (Strings.isNullOrEmpty(answer.getNode().getAnswer().getAuthor().getProfileUrl())) {
            return;
        }
        QuoraAnswer a = quoraAnswerMapper.one(answer.getNode().getAnswer().getAid());
        if (a == null) {

            processU(answer.getNode().getAnswer().getAuthor(), answer.getNode().getAnswer().getAuthorCredential());

            QuoraAnswer quoraAnswer = new QuoraAnswer();

            quoraAnswer.setId(answer.getNode().getAnswer().getAid());
            quoraAnswer.setIdx(answer.getNode().getAnswer().getId());
            GetAnswersRes.AnswerItem.Data.Node.Answer.OriginalQuestionIfDifferent q = answer.getNode().getAnswer().getOriginalQuestionIfDifferent();
            if (q != null && q.getQuestion() != null) { // 说明是关联进来的问题，需要落库相关问题
                processQ(q.getQuestion().getQid(), q.getQuestion().getId(), q.getQuestion().getUrl(), q.getQuestion().getTitle());
                quoraAnswer.setQuestionId(q.getQuestion().getQid());
            } else {
                quoraAnswer.setQuestionId(answer.getNode().getQuestion().getQid());
            }

            quoraAnswer.setUrl(answer.getNode().getAnswer().getUrl());
            quoraAnswer.setViewCount(answer.getNode().getAnswer().getNumViews());
            quoraAnswer.setLikeCount(answer.getNode().getAnswer().getNumUpvotes());
            quoraAnswer.setShareCount(answer.getNode().getAnswer().getNumSharers());

            quoraAnswer.setUserId(answer.getNode().getAnswer().getAuthor().getUid());

            quoraAnswer.setCtime(LocalDateTime.ofEpochSecond(answer.getNode().getAnswer().getCreationTime() / 1000000, 0, ZoneOffset.ofHours(8)));

            String content = answer.getNode().getAnswer().getContent();
            if (Strings.isNullOrEmpty(content)) {
                throw new IllegalStateException("回答为空！answer = " + JSON.toJSONString(answer));
            }
            // content = StringEscapeUtils.unescapeJava(content);
            QuoraAnswerContent answerContent = JSONObject.parseObject(content, QuoraAnswerContent.class);

            List<String> images = Lists.newArrayList(); // 插图
            Map<String, String> quoraToQiniuMap = Maps.newHashMap();
            answerContent.getSections().stream().filter(sc -> sc.getType().equals("image")).forEach(sc -> sc.getSpans().forEach(span -> images.add(span.getModifiers().getImage())));
            if (images.size() > 0) {
                quoraAnswer.setHaveImg(1);
                for (String image : images) {
                    URI imageUri = new URI(image);

                    String imageName = String.format("sharemer/answers%s.jpg", imageUri.getPath());
                    String r = qiniuService.imageUpload(imageName, imageProcessor.getImageByUrl(image));
                    if (Strings.isNullOrEmpty(r)) {
                        throw new IllegalStateException("上传插图失败！url = " + image);
                    }
                    String qiniuUrl = String.format("%s/%s", appProperties.getSharemerUrl(), imageName);
                    quoraToQiniuMap.put(image, qiniuUrl);

                    WechatImage wechatImage = new WechatImage();
                    wechatImage.setOid(quoraAnswer.getId());
                    wechatImage.setOtype(0);
                    wechatImage.setOtherUrl(image);
                    wechatImage.setQiniuUrl(imageName);

                    BufferedImage cover = ImageIO.read(new URL(image));
                    wechatImage.setWidth(cover.getWidth());
                    wechatImage.setHeight(cover.getHeight());

                    wechatImageMapper.insert(wechatImage);
                }
            }

            // 生成中英文内容
            StringBuilder ensb = new StringBuilder();
            StringBuilder cnsb = new StringBuilder();
            StringBuilder preview = new StringBuilder();

            toHtml(ensb, cnsb, preview, answerContent, quoraToQiniuMap);

            quoraAnswer.setContentOri(ensb.toString().replaceAll("\n", "</br>"));
            quoraAnswer.setContentCn(cnsb.toString().replaceAll("\n", "</br>"));

            if (preview.length() > 0) { // 回答预览
                quoraAnswer.setWordCount(preview.length()); // 字节数
                quoraAnswer.setPreview(String.format("%s...", preview.substring(0, Math.min(preview.length(), 150))));
            }

            quoraAnswerMapper.insert(quoraAnswer);
        }
    }

    public void toHtml(StringBuilder ensb, StringBuilder cnsb, StringBuilder preview, QuoraAnswerContent answerContent, Map<String, String> quoraToQiniuMap) throws Exception {
        int ol = 0;
        int ul = 0;
        for (QuoraAnswerContent.Section section : answerContent.getSections()) {

            // 一个section就是一个分p
            if (section.isQuoted()) {
                if (ol > 0) {
                    ensb.append("</ol>");
                    if (cnsb != null) {
                        cnsb.append("</ol>");
                    }
                    ol = 0;
                }
                if (ul > 0) {
                    ensb.append("</ul>");
                    if (cnsb != null) {
                        cnsb.append("</ul>");
                    }
                    ul = 0;
                }
                ensb.append("<blockquote class=\"js_blockquote_wrap\"><section class=\"js_blockquote_digest\">");
                if (cnsb != null) {
                    cnsb.append("<blockquote class=\"js_blockquote_wrap\"><section class=\"js_blockquote_digest\">");
                }
            } else {
                ensb.append("<p>");
                if (cnsb != null) {
                    cnsb.append("<p>");
                }
            }

            if (section.getType().equals("image")) {

                if (ol > 0) {
                    ensb.append("</ol>");
                    if (cnsb != null) {
                        cnsb.append("</ol>");
                    }
                    ol = 0;
                }
                if (ul > 0) {
                    ensb.append("</ul>");
                    if (cnsb != null) {
                        cnsb.append("</ul>");
                    }
                    ul = 0;
                }

                for (QuoraAnswerContent.Section.Span span : section.getSpans()) {
                    ensb.append(String.format("<center><img src = '%s'/></center>", quoraToQiniuMap == null ? span.getModifiers().getImage() : quoraToQiniuMap.get(span.getModifiers().getImage())));
                    if (cnsb != null) {
                        cnsb.append(String.format("<center><img src = '%s'/></center>", quoraToQiniuMap == null ? span.getModifiers().getImage() : quoraToQiniuMap.get(span.getModifiers().getImage())));
                    }
                }
            } else if (section.getType().equals("horizontal-rule")) {
                if (ol > 0) {
                    ensb.append("</ol>");
                    if (cnsb != null) {
                        cnsb.append("</ol>");
                    }
                    ol = 0;
                }
                if (ul > 0) {
                    ensb.append("</ul>");
                    if (cnsb != null) {
                        cnsb.append("</ul>");
                    }
                    ul = 0;
                }
                ensb.append("<hr style = \"width: 148px; \"/>");
                if (cnsb != null) {
                    cnsb.append("<hr style = \"width: 148px; \"/>");
                }
            } else if (section.getType().equals("ordered-list")) {
                if (ul > 0) {
                    ensb.append("</ul>");
                    if (cnsb != null) {
                        cnsb.append("</ul>");
                    }
                    ul = 0;
                }
                if (ol == 0) {
                    ensb.append("<ol class=\"list-paddingleft-1\" style=\"list-style-type: decimal;\">");
                    if (cnsb != null) {
                        cnsb.append("<ol class=\"list-paddingleft-1\" style=\"list-style-type: decimal;\">");
                    }
                }
                ensb.append("<li>");
                if (cnsb != null) {
                    cnsb.append("<li>");
                }
                setSpans(section.getSpans(), ensb, cnsb, preview);
                ensb.append("</li>");
                if (cnsb != null) {
                    cnsb.append("</li>");
                }
                ol += 1;
            } else if (section.getType().equals("unordered-list")) {
                if (ol > 0) {
                    ensb.append("</ol>");
                    if (cnsb != null) {
                        cnsb.append("</ol>");
                    }
                    ol = 0;
                }
                if (ul == 0) {
                    ensb.append("<ul class=\"list-paddingleft-1\" style=\"list-style-type: disc;\">");
                    if (cnsb != null) {
                        cnsb.append("<ul class=\"list-paddingleft-1\" style=\"list-style-type: disc;\">");
                    }
                }
                ensb.append("<li>");
                if (cnsb != null) {
                    cnsb.append("<li>");
                }
                setSpans(section.getSpans(), ensb, cnsb, preview);
                ensb.append("</li>");
                if (cnsb != null) {
                    cnsb.append("</li>");
                }
                ul += 1;
            } else {
                if (ol > 0) {
                    ensb.append("</ol>");
                    if (cnsb != null) {
                        cnsb.append("</ol>");
                    }
                    ol = 0;
                }
                if (ul > 0) {
                    ensb.append("</ul>");
                    if (cnsb != null) {
                        cnsb.append("</ul>");
                    }
                    ul = 0;
                }

                setSpans(section.getSpans(), ensb, cnsb, preview);
            }

            if (section.isQuoted()) {
                ensb.append("</section></blockquote>");
                if (cnsb != null) {
                    cnsb.append("</section></blockquote>");
                }
            } else {
                ensb.append("</p>");
                if (cnsb != null) {
                    cnsb.append("</p>");
                }
            }
        }
    }

    private void setSpans(List<QuoraAnswerContent.Section.Span> spans,
                          StringBuilder ensb,
                          StringBuilder cnsb,
                          StringBuilder preview) throws Exception {
        for (QuoraAnswerContent.Section.Span span : spans) {
            setSpan(span, ensb, cnsb, false);
            ensb.append(span.getText());
            if (cnsb != null) {
                String chinese = youdaoService.translate(span.getText());
                cnsb.append(chinese);
                if (preview != null) {
                    preview.append(chinese);
                }
            }
            setSpan(span, ensb, cnsb, true);
        }
    }

    private void setSpan(QuoraAnswerContent.Section.Span span, StringBuilder ensb, StringBuilder cnsb, boolean off) throws Exception {
        if (!off) { // html标签-开
            if (span.getModifiers().isBold()) {
                ensb.append("<strong>");
                if (cnsb != null) {
                    cnsb.append("<strong>");
                }
            }
            if (span.getModifiers().isItalic()) {
                ensb.append("<em>");
                if (cnsb != null) {
                    cnsb.append("<em>");
                }
            }
            if (span.getModifiers().getLink() != null && !Strings.isNullOrEmpty(span.getModifiers().getLink().getUrl())) {
                ensb.append("<a href=\"")
                        .append(span.getModifiers().getLink().getUrl())
                        .append("\" target=\"_blank\">");
                if (cnsb != null) {
                    cnsb.append("<a href=\"")
                            .append(span.getModifiers().getLink().getUrl())
                            .append("\" target=\"_blank\">");
                }
            }
            if (span.getModifiers().getEmbed() != null && Strings.isNullOrEmpty(span.getModifiers().getEmbed().getImageUrl())) {

                ensb.append("<center><span style=\"color: #a8a8a8;\"><em>")
                        .append("⚠️ Here is an embed card(We can't render it now): ")
                        .append(span.getModifiers().getEmbed().getTitle())
                        .append("</em></center>");

                if (cnsb != null) {
                    cnsb.append("<center><span style=\"color: #a8a8a8;\"><em>")
                            .append("⚠️ 此处为分享卡片（暂不支持样式渲染）：")
                            .append(youdaoService.translate(span.getModifiers().getEmbed().getTitle()))
                            .append("</em></center>");
                }
            }
        } else { // html标签-闭
            if (span.getModifiers().isBold()) {
                ensb.append("</strong>");
                if (cnsb != null) {
                    cnsb.append("</strong>");
                }
            }
            if (span.getModifiers().isItalic()) {
                ensb.append("</em>");
                if (cnsb != null) {
                    cnsb.append("</em>");
                }
            }
            if (span.getModifiers().getLink() != null && !Strings.isNullOrEmpty(span.getModifiers().getLink().getUrl())) {
                ensb.append("</a>");
                if (cnsb != null) {
                    cnsb.append("</a>");
                }
            }
            if (span.getModifiers().getEmbed() != null && Strings.isNullOrEmpty(span.getModifiers().getEmbed().getImageUrl())) {
                ensb.append("</span>");
                if (cnsb != null) {
                    cnsb.append("</span>");
                }
            }
        }
    }

    // 处理用户
    private void processU(GetAnswersRes.AnswerItem.Data.Node.Answer.Author author, GetAnswersRes.AnswerItem.Data.Node.Answer.AuthorCredential credential) throws Exception {
        QuoraUser user = new QuoraUser();
        user.setId(author.getUid());
        user.setIdx(author.getId());
        GetAnswersRes.AnswerItem.Data.Node.Answer.Author.Name name = author.getNames().get(0);
        user.setName(String.format("%s %s", name.getGivenName(), name.getFamilyName()));
        if (credential != null) {
            user.setCredentials(youdaoService.translate(credential.getDescription()));
        }
        user.setFollowers(author.getFollowerCount());
        user.setCtime(LocalDateTime.now());

        user.setUrl(author.getProfileUrl()); // /profile/Nicholas-Maximilian-Dreyer

        QuoraProcessor.Place place = quoraProcessor.getPlace(user.getUrl().substring(9));
        user.setLive(place.getLive());
        user.setCountryId(place.getCountryId());

        URI avatarURI = new URI(author.getProfileImageUrl());
        String img = String.format("sharemer/avatars%s", avatarURI.getPath());
        QuoraUser u = quoraUserMapper.one(user.getId());
        if (u == null || Strings.isNullOrEmpty(u.getAvatar()) || !u.getAvatar().contains(avatarURI.getPath())) {
            String r = qiniuService.imageUpload(img, imageProcessor.getImageByUrl(author.getProfileImageUrl()));
            if (r == null) {
                throw new IllegalStateException("上传头像出错！" + author.getProfileImageUrl());
            }
            user.setAvatar(img);
        }
        if (u == null) {
            quoraUserMapper.insert(user);
        } else {
            quoraUserMapper.update(user);
        }
    }
}
