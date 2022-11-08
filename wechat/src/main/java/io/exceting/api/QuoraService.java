package io.exceting.api;

import io.exceting.common.Constants;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.concurrent.TimeUnit;

public class QuoraService {

    private static final QuoraApi quoraApi;

    public static final QuoraService INSTANT;

    static {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.QUORA_URL)
                .build();
        quoraApi = retrofit.create(QuoraApi.class);

        INSTANT = new QuoraService();
    }

    public String getAnswer(String qid, String aid) throws Exception {
        Call<ResponseBody> call = quoraApi.getAnswer(qid, aid);
        Response<ResponseBody> response = call.execute();
        if (response.isSuccessful() && response.body() != null) {
            return response.body().string();
        } else {
            return null;
        }
    }

}
