package io.exceting.processors;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import io.exceting.api.WechatService;
import io.exceting.common.Constants;
import io.exceting.model.AccessToken;
import io.exceting.model.MediaUpload;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class AccessTokenProcessor {

    public static final AccessTokenProcessor INSTANT = new AccessTokenProcessor();

    private AccessTokenProcessor() {
    }

    public static void main(String[] args) throws Exception {
        INSTANT.attachToken("C:\\Users\\18073\\Desktop\\wechatAppInfo.txt", "C:\\Users\\18073\\Desktop\\wechatToken.txt");

        MediaUpload upload = WechatService.INSTANT.imageUpload(Constants.VAL_MAP.get(Constants.VAL_MAP_KEY_TOKEN),
                new File("D:\\29b7db477fcbec65844bc303fe306754.jpg"));

        System.out.println("上传成功！url = " + upload.getUrl());
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
