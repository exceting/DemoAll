package io.exceting.api;

import io.exceting.model.TranslateResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface YoudaoApi {

    @POST("/api")
    @FormUrlEncoded
    Call<TranslateResponse> translate(@Field(value = "q") String q,
                                      @Field(value = "from") String from,
                                      @Field(value = "to") String to,
                                      @Field(value = "appKey") String appKey,
                                      @Field(value = "salt") String salt,
                                      @Field(value = "sign") String sign,
                                      @Field(value = "signType") String signType,
                                      @Field(value = "curtime") String curtime);

}
