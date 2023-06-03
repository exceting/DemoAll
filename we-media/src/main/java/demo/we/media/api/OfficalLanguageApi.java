package demo.we.media.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OfficalLanguageApi {

    @GET("/web/gjhdq_676201/gj_676203/{area_id}")
    Call<ResponseBody> getCountries(@Path("area_id") String areaId);

}
