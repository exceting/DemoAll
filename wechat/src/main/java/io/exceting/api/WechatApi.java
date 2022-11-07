package io.exceting.api;

import io.exceting.model.AccessToken;
import io.exceting.model.AddDraft;
import io.exceting.model.MediaUpload;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface WechatApi {

    // 获取access_token
    @GET("/cgi-bin/token")
    Call<AccessToken> accessToken(@Query("grant_type") String grantType,
                                  @Query("appid") String appid,
                                  @Query("secret") String secret);

    // 上传永久素材
    @Multipart
    @POST("/cgi-bin/material/add_material")
    Call<MediaUpload> imageUpload(@Query("access_token") String accessToken,
                                  @Query("type") String type,
                                  @Part MultipartBody.Part media);

    // 新建草稿
    @POST("/cgi-bin/draft/add")
    Call<AddDraft> addDraft(@Query("access_token") String accessToken,
                            @Body RequestBody body);

}
