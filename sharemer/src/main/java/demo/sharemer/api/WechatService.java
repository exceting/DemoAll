package demo.sharemer.api;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import demo.sharemer.config.AppProperties;
import demo.sharemer.model.http.AccessToken;
import demo.sharemer.model.http.AddDraft;
import demo.sharemer.model.http.AddDraftReq;
import demo.sharemer.model.http.MediaUpload;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class WechatService {

    private static WechatApi wechatApi;

    @Resource
    private AppProperties appProperties;

    @PostConstruct
    private void init() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(appProperties.getWechatUrl())
                .addConverterFactory(new FastJsonConverterFactory())
                .build();
        wechatApi = retrofit.create(WechatApi.class);
    }

    private WechatService() {
    }

    public AccessToken getAccessToken(String appId, String appSecret) throws Exception {
        Call<AccessToken> call = wechatApi.accessToken("client_credential", appId, appSecret);
        Response<AccessToken> response = call.execute();
        if (response.isSuccessful() && response.body() != null && response.body().getErrcode() == null) {
            return response.body();
        } else {
            return null;
        }
    }

    public MediaUpload imageUpload(String accessToken, String type, String fileName, byte[] file) throws Exception {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        Call<MediaUpload> call = wechatApi.imageUpload(accessToken, type, MultipartBody.Part.createFormData("media", fileName, requestFile));
        Response<MediaUpload> response = call.execute();
        if (response.isSuccessful() && response.body() != null && response.body().getErrcode() == null) {
            return response.body();
        } else {
            return null;
        }
    }

    public AddDraft addDraft(String accessToken,
                             String title,
                             String author,
                             String content,
                             String coverMediaId) throws Exception {

        AddDraftReq.Article article = new AddDraftReq.Article();
        article.setTitle(title);
        article.setAuthor(author);
        article.setContent(content);
        article.setContentSourceUrl(null);
        article.setThumbMediaId(coverMediaId);
        article.setNeedOpenComment(0);
        article.setOnlyFansCanComment(0);

        AddDraftReq req = new AddDraftReq();
        req.setArticles(Lists.newArrayList(article));

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), JSON.toJSONString(req));

        Call<AddDraft> call = wechatApi.addDraft(accessToken, body);
        Response<AddDraft> response = call.execute();
        if (response.isSuccessful() && response.body() != null && response.body().getErrcode() == null) {
            return response.body();
        } else {
            return null;
        }
    }
}
