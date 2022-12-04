package demo.sharemer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
@Data
public class AppProperties {

    private String wechatUrl;
    private String quoraUrl;
    private String youdaoUrl;
    private String qiniuUrl;
    private String sharemerUrl;
    private String quoraHeaderBg;
    private String quoraDefaultCover;
    private String quoraTailBg;
    private String appIdPath;
    private String accessTokenPath;
    private String youdaoPath;
    private String qiniuPath;

    // Quora爬虫所需参数
    private String quoraFormKey; // 在回答列表页的源代码里可以拿到（会不会过期需观察）
    private String getAnswersHash; // 登录性未知，时效性非常长（会不会过期需观察）
    private String quoraCookie; // 跟登录有关，必传（也可能是一个有时效性的死值，需观察）
}
