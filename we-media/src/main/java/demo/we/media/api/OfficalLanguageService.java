package demo.we.media.api;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

@Component
public class OfficalLanguageService {
    private static OfficalLanguageApi officalLanguageApi;

    @PostConstruct
    private void init() throws Exception {
        OkHttpClient client = new OkHttpClient.Builder()
                .sslSocketFactory(createInsecureSSLSocketFactory(), new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {}

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) {}

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                })
                .hostnameVerifier((hostname, session) -> true)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://www.mfa.gov.cn")
                .build();
        officalLanguageApi = retrofit.create(OfficalLanguageApi.class);
    }

    private SSLSocketFactory createInsecureSSLSocketFactory() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {}

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) {}

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                }
        };

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, new SecureRandom());

        return sslContext.getSocketFactory();
    }

    public String getCountries(String areaId) throws Exception {
        Call<ResponseBody> call = officalLanguageApi.getCountries(areaId);
        Response<ResponseBody> response = call.execute();
        if (response.isSuccessful() && response.body() != null) {
            return response.body().string();
        } else {
            return null;
        }
    }
}
