package demo.sharemer.job;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import demo.sharemer.api.WechatService;
import demo.sharemer.config.AppProperties;
import demo.sharemer.model.http.AccessToken;
import demo.sharemer.utils.Constants;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@Component
public class InitJob {

    @Resource
    private AppProperties appProperties;

    @Resource
    private WechatService wechatService;

    @PostConstruct
    private void init() throws Exception {
        attachYoudaoKey();
        attachQiniuKey();
    }

    public void attachQiniuKey() throws Exception {
        String appKey = Constants.VAL_MAP.get(Constants.VAL_MAP_QINIU_APPKEY);
        String secretKey = Constants.VAL_MAP.get(Constants.VAL_MAP_QINIU_SECRETKEY);
        if (Strings.isNullOrEmpty(appKey) || Strings.isNullOrEmpty(secretKey)) {
            List<String> lines = Lists.newArrayList();
            try (Stream<String> in = Files.lines(Paths.get(appProperties.getQiniuPath()))) {
                in.forEach(lines::add);
            }
            if (lines.size() == 2) {
                Constants.VAL_MAP.put(Constants.VAL_MAP_QINIU_APPKEY, lines.get(0));
                Constants.VAL_MAP.put(Constants.VAL_MAP_QINIU_SECRETKEY, lines.get(1));
            }
        }
    }

    public void attachYoudaoKey() throws Exception {
        String appKey = Constants.VAL_MAP.get(Constants.VAL_MAP_YOUDAO_APPKEY);
        String secretKey = Constants.VAL_MAP.get(Constants.VAL_MAP_YOUDAO_SECRETKEY);
        if (Strings.isNullOrEmpty(appKey) || Strings.isNullOrEmpty(secretKey)) {
            List<String> lines = Lists.newArrayList();
            try (Stream<String> in = Files.lines(Paths.get(appProperties.getYoudaoPath()))) {
                in.forEach(lines::add);
            }
            if (lines.size() == 2) {
                Constants.VAL_MAP.put(Constants.VAL_MAP_YOUDAO_APPKEY, lines.get(0));
                Constants.VAL_MAP.put(Constants.VAL_MAP_YOUDAO_SECRETKEY, lines.get(1));
            }
        }
    }

    @Scheduled(fixedRate = 60 * 60 * 1000, initialDelay = 0)
    public void attachAccessToken() throws Exception {
        String token = readAccessToken(appProperties.getAccessTokenPath());
        if (Strings.isNullOrEmpty(token)) {
            refreshTokenAndApp();
            token = readAccessToken(appProperties.getAccessTokenPath());
        }
        Constants.VAL_MAP.put(Constants.VAL_MAP_ACCESS_TOKEN, token);
    }

    private String readAccessToken(String accessTokenPath) throws Exception {
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

    private void refreshTokenAndApp() throws Exception {

        List<String> lines = Lists.newArrayList();
        try (Stream<String> in = Files.lines(Paths.get(appProperties.getAppIdPath()))) {
            in.forEach(lines::add);
        }

        if (lines.size() != 2) {
            throw new IllegalStateException("Get app id and app secret error!");
        }
        Constants.VAL_MAP.put(Constants.VAL_MAP_APP_ID, lines.get(0));
        Constants.VAL_MAP.put(Constants.VAL_MAP_APP_SECRET, lines.get(1));
        AccessToken accessToken = wechatService.getAccessToken(Constants.VAL_MAP.get(Constants.VAL_MAP_APP_ID),
                Constants.VAL_MAP.get(Constants.VAL_MAP_APP_SECRET));
        if (accessToken == null) {
            throw new IllegalStateException("Get access token error!");
        }

        String newContent = String.format("%s\n%s\n%s", accessToken.getAccessToken(), accessToken.getExpiresIn(), System.currentTimeMillis());

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(appProperties.getAccessTokenPath()), StandardCharsets.UTF_8)) {
            writer.write(newContent);
        }
    }

}
