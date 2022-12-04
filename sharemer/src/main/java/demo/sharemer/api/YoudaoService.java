package demo.sharemer.api;

import com.google.common.base.Strings;
import demo.sharemer.config.AppProperties;
import demo.sharemer.model.http.TranslateResponse;
import demo.sharemer.utils.Constants;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Component
public class YoudaoService {

    private static YoudaoApi youdaoApi;

    @Resource
    private AppProperties appProperties;

    @PostConstruct
    private void init() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(appProperties.getYoudaoUrl())
                .addConverterFactory(new FastJsonConverterFactory())
                .build();
        youdaoApi = retrofit.create(YoudaoApi.class);
    }

    public String translate(String q) throws Exception {

        if (Strings.isNullOrEmpty(q)) {
            return q;
        }

        String curTime = String.valueOf(System.currentTimeMillis() / 1000);
        String salt = UUID.randomUUID().toString();

        // 计算签名
        String sign = getSha256Str(Constants.VAL_MAP.get(Constants.VAL_MAP_YOUDAO_APPKEY)
                + getInput(q) + salt + curTime + Constants.VAL_MAP.get(Constants.VAL_MAP_YOUDAO_SECRETKEY));

        Call<TranslateResponse> call = youdaoApi.translate(q, "auto", "zh-CHS",
                Constants.VAL_MAP.get(Constants.VAL_MAP_YOUDAO_APPKEY), salt, sign, "v3", curTime);
        Response<TranslateResponse> response = call.execute();
        if (response.isSuccessful() && response.body() != null && response.body().getErrorCode().equals("0")) {
            String chinese = response.body().getTranslation();
            if (chinese.length() >= 4) {
                chinese = chinese.substring(2, chinese.length() - 2);
            }
            return chinese;
        } else {
            return q;
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
