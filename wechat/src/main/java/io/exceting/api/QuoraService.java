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
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .callTimeout(120, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.QUORA_URL)
                .client(okHttpClient)
                .build();
        quoraApi = retrofit.create(QuoraApi.class);

        INSTANT = new QuoraService();
    }

    public String getAnswer(String qid, String aid) throws Exception {
        Call<ResponseBody> call = quoraApi.getAnswer(qid, aid);
        long s = System.currentTimeMillis();
        try {
            Response<ResponseBody> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            } else {
                return null;
            }
        } finally {
            System.out.println("耗时：" + (System.currentTimeMillis() - s));
        }
    }

}
