package io.exceting.api;

import io.exceting.common.Constants;
import io.exceting.model.TranslateResponse;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class YoudaoService {

    private static final YoudaoApi youdaoApi;

    public static final YoudaoService INSTANT;

    static {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.YOUDAO_URL)
                .addConverterFactory(new FastJsonConverterFactory())
                .build();
        youdaoApi = retrofit.create(YoudaoApi.class);

        INSTANT = new YoudaoService();
    }

    public TranslateResponse translate(String q) throws Exception {
        String curTime = String.valueOf(System.currentTimeMillis() / 1000);
        String salt = UUID.randomUUID().toString();

        // 计算签名
        String sign = getSha256Str(Constants.VAL_MAP.get(Constants.VAL_MAP_YOUDAO_APPKEY)
                + getInput(q) + salt + curTime + Constants.VAL_MAP.get(Constants.VAL_MAP_YOUDAO_SECRETKEY));

        Call<TranslateResponse> call = youdaoApi.translate(q, "auto", "zh-CHS",
                Constants.VAL_MAP.get(Constants.VAL_MAP_YOUDAO_APPKEY), salt, sign, "v3", curTime);
        Response<TranslateResponse> response = call.execute();
        if (response.isSuccessful() && response.body() != null && response.body().getErrorCode().equals("0")) {
            return response.body();
        } else {
            return null;
        }
    }

    private String getInput(String q) {
        if (q.length() <= 20) {
            return q;
        }
        return q.substring(0, 10) + q.length() + q.substring(q.length() - 10);
    }

    public String getSha256Str(String str) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    private String byte2Hex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        String temp;
        for (byte aByte : bytes) {
            temp = Integer.toHexString(aByte & 0xFF);
            if (temp.length() == 1) {
                stringBuilder.append("0");
            }
            stringBuilder.append(temp);
        }
        return stringBuilder.toString();
    }

}
