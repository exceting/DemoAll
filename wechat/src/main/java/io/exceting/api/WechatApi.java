package io.exceting.api;

import io.exceting.model.AccessToken;
import io.exceting.model.MediaUpload;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface WechatApi {

    @GET("/cgi-bin/token")
    Call<AccessToken> accessToken(@Query("grant_type") String grantType,
                                  @Query("appid") String appid,
                                  @Query("secret") String secret);

    @Multipart
    @POST("/cgi-bin/media/uploadimg")
    Call<MediaUpload> imageUpload(@Query("access_token") String accessToken,
                                  @Part MultipartBody.Part media);

}
