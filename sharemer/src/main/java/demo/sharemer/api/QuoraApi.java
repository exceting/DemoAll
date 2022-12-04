package demo.sharemer.api;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface QuoraApi {

    @GET("/{qid}/answer/{aid}")
    Call<ResponseBody> getAnswer(@Path("qid") String qid,
                                 @Path("aid") String aid);

    @GET("/profile/{name}")
    Call<ResponseBody> getProfileByUserName(@Path("name") String name);

    @POST("/graphql/gql_para_POST?q=QuestionPagedListPaginationQuery")
    Call<ResponseBody> getAnswers(@Header("quora-formkey") String quoraFormkey,
                                  @Header("Cookie") String cookie,
                                  @Body RequestBody requestBody);

}
