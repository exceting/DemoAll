package io.exceting.processors;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import com.vladsch.flexmark.util.misc.Extension;
import io.exceting.api.WechatService;
import io.exceting.common.Constants;
import io.exceting.model.AccessToken;
import io.exceting.model.AddDraft;
import io.exceting.model.MediaUpload;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class AccessTokenProcessor {

    public static final AccessTokenProcessor INSTANT = new AccessTokenProcessor();

    private AccessTokenProcessor() {
    }

    public static void main(String[] args) throws Exception {
        AccessTokenProcessor.INSTANT.attachToken("/Users/sunqinwen/Downloads/wechatAppInfo.txt", "/Users/sunqinwen/Downloads/wechatToken.txt");

        MediaUpload upload = WechatService.INSTANT.imageUpload(Constants.VAL_MAP.get(Constants.VAL_MAP_KEY_TOKEN),
                "image", new File("/Users/sunqinwen/Downloads/hahaha.png"));

        System.out.println("上传成功！url = " + upload.getUrl() + "    mediaId = " + upload.getMediaId());

        List<String> allLines = Files.readAllLines(Paths.get("/Users/sunqinwen/Downloads/LV1-3：java中的运算符.md"));
        String content = Joiner.on("\n").join(allLines);
        MutableDataSet options = new MutableDataSet();
        options.setFrom(ParserEmulationProfile.MARKDOWN);
        options.set(Parser.EXTENSIONS, Arrays.asList(new Extension[]{TablesExtension.create()}));
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        Node document = parser.parse(content);
        String html = renderer.render(document);

        String html2 = "<h1>哈哈哈哈哈哈哈~ger</h1><br/><hr/><a href=\"https://www.bilibili.com\">哔哩哔哩</a><br/><span style='font-size:18px; color: red'>这是一张图片：</span><br/><img src='" + upload.getUrl() + "'/>";

        AddDraft draft = WechatService.INSTANT.addDraft(Constants.VAL_MAP.get(Constants.VAL_MAP_KEY_TOKEN),
                "图文消息测试~~",
                "Java进化论",
                html2,
                upload.getMediaId());

        System.out.println("draft mediaId = " + draft.getMediaId());
    }

    public void attachToken(String appIdPath, String accessTokenPath) throws Exception {
        String token = readToken(accessTokenPath);
        if (Strings.isNullOrEmpty(token)) {
            refreshTokenAndApp(appIdPath, accessTokenPath);
            token = readToken(accessTokenPath);
        }
        Constants.VAL_MAP.put(Constants.VAL_MAP_KEY_TOKEN, token);
    }

    private String readToken(String accessTokenPath) throws Exception {
        File file = new File(accessTokenPath);
        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new IllegalStateException("Create token file error!");
            }
        }

        List<String> lines = Lists.newArrayList();
        try (Stream<String> in = Files.lines(file.toPath())) {
            in.forEach(lines::add);
        }

        if (lines.size() != 3) {
            return null;
        }

        int time = (int) ((System.currentTimeMillis() - Long.parseLong(lines.get(2))) / 1000);

        if (time > (Integer.parseInt(lines.get(1)) - 600)) {
            return null;
        }

        return lines.get(0);
    }

    private void refreshTokenAndApp(String appIdPath, String accessTokenPath) throws Exception {

        List<String> lines = Lists.newArrayList();
        try (Stream<String> in = Files.lines(Paths.get(appIdPath))) {
            in.forEach(lines::add);
        }

        if (lines.size() != 2) {
            throw new IllegalStateException("Get app id and app secret error!");
        }
        Constants.VAL_MAP.put(Constants.VAL_MAP_APP_ID, lines.get(0));
        Constants.VAL_MAP.put(Constants.VAL_MAP_APP_SECRET, lines.get(1));
        AccessToken accessToken = WechatService.INSTANT.getAccessToken(Constants.VAL_MAP.get(Constants.VAL_MAP_APP_ID),
                Constants.VAL_MAP.get(Constants.VAL_MAP_APP_SECRET));
        if (accessToken == null) {
            throw new IllegalStateException("Get access token error!");
        }

        String newContent = String.format("%s\n%s\n%s", accessToken.getAccessToken(), accessToken.getExpiresIn(), System.currentTimeMillis());

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(accessTokenPath), StandardCharsets.UTF_8)) {
            writer.write(newContent);
        }
    }

}
