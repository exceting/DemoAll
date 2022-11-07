package io.exceting.api;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import io.exceting.common.Constants;
import io.exceting.model.AccessToken;
import io.exceting.model.AddDraft;
import io.exceting.model.AddDraftReq;
import io.exceting.model.MediaUpload;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.File;

public class WechatService {

    private static final WechatApi wechatApi;

    public static final WechatService INSTANT;

    static {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.WECHAT_URL)
                .addConverterFactory(new FastJsonConverterFactory())
                .build();
        wechatApi = retrofit.create(WechatApi.class);

        INSTANT = new WechatService();
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

    public MediaUpload imageUpload(String accessToken, String type, File file) throws Exception {
        Call<MediaUpload> call = wechatApi.imageUpload(accessToken, type, prepareFilePart("media", file));
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

    private MultipartBody.Part prepareFilePart(String partName, File file) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }
}
