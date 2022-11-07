package io.exceting.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface QuoraApi {

    @GET("/{qid}/answer/{aid}")
    Call<ResponseBody> getAnswer(@Path("qid") String qid,
                                 @Path("aid") String aid);

}
