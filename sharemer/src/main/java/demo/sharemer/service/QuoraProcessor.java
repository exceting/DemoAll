package demo.sharemer.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import demo.sharemer.api.QuoraService;
import demo.sharemer.api.WechatService;
import demo.sharemer.config.AppProperties;
import demo.sharemer.mapper.quora.*;
import demo.sharemer.mapper.worlds.CountryMapper;
import demo.sharemer.model.*;
import demo.sharemer.model.http.AddDraft;
import demo.sharemer.model.http.MediaUpload;
import demo.sharemer.model.http.QuoraAnswerContent;
import demo.sharemer.model.res.AnswerResp;
import demo.sharemer.utils.Constants;
import demo.sharemer.utils.Page;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@Service
public class QuoraProcessor {

    @Resource
    private QuoraService quoraService;

    @Resource
    private AppProperties appProperties;

    @Resource
    private QuoraQuestionMapper quoraQuestionMapper;

    @Resource
    private QuoraAnswerMapper quoraAnswerMapper;

    @Resource
    private QuoraUserMapper quoraUserMapper;

    @Resource
    private WechatImageMapper wechatImageMapper;

    @Resource
    private ImageProcessor imageProcessor;

    @Resource
    private CountryMapper countryMapper;

    @Resource
    private WechatTaskMapper wechatTaskMapper;

    @Resource
    private WechatService wechatService;

    private java.util.List<Country> countries;
    private java.util.List<Country> states;
    private List<Country> cities;

    private final DateTimeFormatter YYYY_MM_DD_HH_MM_SS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @PostConstruct
    public void init() {
        long s = System.currentTimeMillis();
        String topIds = "101, 233, 232, 39, 45, 109, 199";
        countries = countryMapper.getCountries(String.format("IN (%s)", topIds)); // 将印、美、英、加、中、日、新加坡 提权
        countries.addAll(countryMapper.getCountries(String.format("NOT IN (%s)", topIds)));

        states = countryMapper.getStates(String.format("IN (%s)", topIds)); // 将印、美、英、加、中、日、新加坡 提权
        states.addAll(countryMapper.getStates(String.format("NOT IN (%s)", topIds)));

        cities = countryMapper.getCities(String.format("IN (%s)", topIds)); // 将印、美、英、加、中、日、新加坡 提权
        cities.addAll(countryMapper.getCities(String.format("NOT IN (%s)", topIds)));

        log.info("国家构建完成！耗时 = " + (System.currentTimeMillis() - s));
    }

    public void exportToWechat(Long qid, Long aid) throws Exception {
        wechatHtml(qid, Lists.newArrayList(aid));
    }

    private void wechatHtml(Long qid, List<Long> aids) throws Exception {
        // TODO 先清除旧


        StringBuilder sb = new StringBuilder();

        QuoraQuestion question = quoraQuestionMapper.one(qid);
        if (question == null) {
            return;
        }

        Integer total = quoraAnswerMapper.getAnswersByQidCount(qid);

        List<QuoraUser> users = Lists.newArrayList();
        List<WechatTask> tasks = Lists.newArrayList();
        WechatImage coverImg = null;
        int i = 1;
        int size = aids.size();
        for (Long aid : aids) {
            QuoraAnswer answer = quoraAnswerMapper.one(aid);
            if (answer != null) {
                // 用户信息
                QuoraUser quoraUser = quoraUserMapper.one(answer.getUserId());
                users.add(quoraUser);
                // 名次
                Integer mc = quoraAnswerMapper.getAnswersByQidAndLC(qid, answer.getLikeCount());
                // 插图
                List<WechatImage> images = wechatImageMapper.getImgsByAid(aid, null);

                Country country = getCountryById(quoraUser.getCountryId());
                if (country == null) {
                    country = new Country();
                    country.setCnName("未知地区");
                    country.setIcon(String.format("%s/%s", appProperties.getSharemerUrl(), "sharemer/flags/antarctica.png"));
                }
                String vc;
                if (answer.getViewCount() > 1000) {
                    vc = String.format("%sK", answer.getViewCount() / 1000);
                } else {
                    vc = String.valueOf(answer.getViewCount());
                }

                String lc;
                if (answer.getLikeCount() > 1000) {
                    lc = String.format("%sK", answer.getLikeCount() / 1000);
                } else {
                    lc = String.valueOf(answer.getLikeCount());
                }

                // 版头
                MediaUpload headerImg = imageProcessor.mediaUpload(String.format("%s.jpg", UUID.randomUUID()),
                        getHeaderImg(quoraUser.getName(),
                                String.format("%s/%s", appProperties.getSharemerUrl(), quoraUser.getAvatar()),
                                country.getCnName(), String.format("%s/%s", appProperties.getSharemerUrl(), country.getIcon()),
                                vc, lc, String.format("%s/%s", mc, total)));

                log.info("版头生成并上传完成！mediaID = {}, url = {}", headerImg.getMediaId(), headerImg.getUrl());

                // 上传插图
                Map<String, String> qiniuToWechatMap = Maps.newHashMap();
                if (images != null && images.size() > 0) {
                    for (WechatImage image : images) {
                        if (coverImg == null) {
                            coverImg = image;
                        }
                        String qiniuUrl = String.format("%s/%s", appProperties.getSharemerUrl(), image.getQiniuUrl());
                        MediaUpload upload = imageProcessor.mediaUpload(String.format("%s.jpg", UUID.randomUUID()),
                                imageProcessor.getImageByUrl(qiniuUrl));
                        qiniuToWechatMap.put(qiniuUrl, upload.getUrl());

                        WechatImage updateOne = new WechatImage();
                        updateOne.setId(image.getId());
                        updateOne.setMediaId(upload.getMediaId());
                        wechatImageMapper.update(updateOne);
                        log.info("图片：{}  上传成功！mediaID = {}, url = {}", qiniuUrl, upload.getMediaId(), upload.getUrl());
                    }
                }
                log.info("图片全部上传完成！现在开始生成文本");
                sb.append(String.format("<center><img src = '%s'/></center>", headerImg.getUrl()));
                qiniuToWechatMap.forEach((k, v) -> answer.setContentCn(answer.getContentCn().replace(k, v)));
                sb.append(answer.getContentCn().replaceAll("<p>", "<p style='margin-bottom: 20px'>"));
                sb.append("</br><span style=\"color: rgb(178, 178, 178);\">")
                        .append("作者：")
                        .append(quoraUser.getName())
                        .append("</br>")
                        .append("时间：")
                        .append(YYYY_MM_DD_HH_MM_SS.format(answer.getCtime()))
                        .append("</span>");
                if (i != size) {
                    sb.append("</br></br><hr/></br></br>");
                } else {
                    sb.append(String.format("<br/><center><img src = '%s'/></center>", appProperties.getQuoraTailBg()));
                }
            }

            WechatTask task = new WechatTask();
            task.setAnswerId(aid);
            task.setQuestionId(qid);
            tasks.add(task);
            i++;
        }

        // 封面
        MediaUpload cover = null;
        if (coverImg != null) {
            cover = imageProcessor.mediaUpload(String.format("%s.jpg", UUID.randomUUID()),
                    getCover(String.format("%s/%s", appProperties.getSharemerUrl(), coverImg.getQiniuUrl())));
        }

        String title = String.format((users.size() > 1 ? "Quora问题：%s-来自%s等人的回答" : "Quora问题：%s-来自%s的回答"), question.getTitleCn(), users.get(0).getName());

        AddDraft draft = wechatService.addDraft(Constants.VAL_MAP.get(Constants.VAL_MAP_ACCESS_TOKEN),
                title, null, sb.toString(), cover == null ? appProperties.getQuoraDefaultCover() : cover.getMediaId());

        System.out.println("上传成功！草稿mediaId=" + draft.getMediaId());

        tasks.forEach(t -> {
            t.setMediaId(draft.getMediaId());
            wechatTaskMapper.insert(t);
        });
    }

    public Page<AnswerResp> getAnswersByQid(Long qid, Integer pn, Integer ps) {
        Page<AnswerResp> result = new Page<>(pn, ps, quoraAnswerMapper.getAnswersByQidCount(qid));

        int offset = (pn - 1) * ps;

        List<AnswerResp> answerResps = quoraAnswerMapper.getAnswersByQid(qid, offset, ps);
        if (answerResps != null && answerResps.size() > 0) {
            List<CompletableFuture<?>> futures = Lists.newArrayList();
            // 补充图片和国家信息
            answerResps.forEach(a -> {
                futures.add(CompletableFuture.runAsync(() -> {
                    Country c = null;
                    if (a.getUser().getCountryId() != null && a.getUser().getCountryId() > 0) {
                        c = getCountryById(a.getUser().getCountryId());
                    }
                    if (c == null) {
                        a.getUser().setCountryName("未知地区");
                        a.getUser().setFlag("/sharemer/flags/antarctica.png");
                    } else {
                        a.getUser().setCountryName(c.getCnName());
                        a.getUser().setFlag(c.getIcon());
                    }
                }));

                futures.add(CompletableFuture.runAsync(() -> {
                    List<WechatImage> images = wechatImageMapper.getImgsByAid(a.getAid(), 4);
                    if (images != null && images.size() > 0) {
                        a.setImgs(images.stream().map(WechatImage::getQiniuUrl).collect(Collectors.toList()));
                        a.setImgCount(wechatImageMapper.getImgCountByAid(a.getAid()));
                    }
                }));
            });

            futures.forEach(CompletableFuture::join);
        }

        result.setData(answerResps);

        return result;
    }

    public QuoraAnswerContent getAnswerByUrl(String qid, String aid) throws Exception {
        String body = quoraService.getAnswer(qid, aid);
        body = body.substring(body.indexOf("{\\\"data\\\":{\\\"answer"));
        body = StringEscapeUtils.unescapeJava(body.substring(0, body.indexOf("}}\");") + 2));

        JSONObject jsonObject = JSONObject.parseObject(body);
        return JSONObject.parseObject(jsonObject.getJSONObject("data").getJSONObject("answer").getJSONObject("content").toJSONString(), QuoraAnswerContent.class);
    }

    public Place getPlace(String name) throws Exception {
        Place place = new Place();

        String live = this.getLivesByUserName(name);
        place.setLive(live);
        if (!Strings.isNullOrEmpty(live)) {
            final String w = live.toLowerCase();

            for (Country c : countries) {
                // 先匹配国家
                if (w.contains(c.getName())) {
                    place.setCountryId(c.getId());
                    return place;
                }
                String alias = c.getAlias();
                if (!Strings.isNullOrEmpty(alias)) {
                    String[] alia = alias.split(",");
                    for (String a : alia) {
                        if (w.contains(a)) {
                            place.setCountryId(c.getId());
                            return place;
                        }
                    }
                }
            }
            for (Country c : states) {
                // 然后匹配州/省
                if (w.contains(c.getName())) {
                    place.setCountryId(c.getId());
                    return place;
                }
            }
            for (Country c : cities) {
                // 最后匹配城市
                if (w.contains(c.getName())) {
                    place.setCountryId(c.getId());
                    return place;
                }
            }
        }

        place.setCountryId(0);
        return place;
    }

    @Setter
    @Getter
    public static class Place {
        private String live;
        private Integer countryId;
    }

    private Country getCountryById(Integer countryId) {
        for (Country c : countries) {
            if (c.getId().equals(countryId)) {
                return c;
            }
        }
        return null;
    }

    private String getLivesByUserName(String name) throws Exception {
        String body = quoraService.getProfileByUserName(name);
        if (!Strings.isNullOrEmpty(body)) {
            try {
                body = body.substring(body.indexOf("\\\"location\\\":{"));
                body = body.substring(0, body.indexOf("}}]") + 3);
                return body.substring(body.indexOf("\\\"name\\\":") + 11, body.indexOf("\\\"}}]"));
            } catch (StringIndexOutOfBoundsException e) {
                return null;
            }
        }

        return null;
    }

    // 生成1:1封面
    public byte[] getCover(String url) throws Exception {
        BufferedImage cover = ImageIO.read(new URL(url));

        int w = cover.getWidth();
        int h = cover.getHeight();
        int x = 0;
        int y = 0;
        int s = w;

        if (w > h) {
            x = (w - h) / 2;
            s = h;
        }

        if (w < h) {
            y = (h - w) / 2;
        }

        BufferedImage bf = new BufferedImage(s, s, cover.getType());
        Graphics2D gh = bf.createGraphics();
        gh.drawImage(cover.getScaledInstance(w, h, Image.SCALE_SMOOTH), 0, 0, s, s, x, y, s + x, s + y, null);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bf, "jpg", os);
        return os.toByteArray();
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

        gh.fillRect(0, 0, 701, 246);

        gh.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        BufferedImage headImage = ImageIO.read(new URL(appProperties.getQuoraHeaderBg()));
        gh.drawImage(headImage, 0, 0, 701, 246, null);

        BufferedImage avatar = ImageIO.read(new URL(avatarUrl));
        avatar = convertCircular(avatar);

        gh.drawImage(avatar, 312, 10, 75, 75, null);

        gh.setStroke(new BasicStroke(2f));
        gh.setColor(new Color(250, 238, 182));
        gh.drawOval(312, 10, 75, 75);

        // 坐标
        BufferedImage flagIcon = ImageIO.read(new URL(countryFlagUrl));
        gh.drawImage(flagIcon, 118, 106, 35, 35, null);
        gh.setFont(new Font("微软雅黑", Font.ITALIC, 15));
        gh.setColor(new Color(136, 136, 136));
        gh.drawString(String.format("（%s）", country), 148, 131);

        // 答主
        gh.setFont(new Font("微软雅黑", Font.PLAIN, 21));
        gh.setColor(new Color(3, 84, 110));
        gh.drawString(userName, 355, 131);

        // 浏览
        gh.setFont(new Font("微软雅黑", Font.BOLD, 25));
        gh.setColor(new Color(255, 183, 0));
        gh.drawString(views, 118, 182);

        // 获赞
        gh.drawString(likes, 355, 182);

        // 排名
        gh.drawString(rank, 578, 182);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bi, "png", os);
        return os.toByteArray();
    }

    // 让图片变成圆形
    public BufferedImage convertCircular(BufferedImage bi1) {
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
