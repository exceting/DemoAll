package demo.sharemer.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import demo.sharemer.config.AppProperties;
import demo.sharemer.model.http.GetAnswersReq;
import demo.sharemer.model.http.GetAnswersRes;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Component
public class QuoraService {

    private static QuoraApi quoraApi;

    @Resource
    private AppProperties appProperties;

    @PostConstruct
    private void init() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(appProperties.getQuoraUrl())
                .build();
        quoraApi = retrofit.create(QuoraApi.class);
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

    public String getProfileByUserName(String name) throws Exception {
        Call<ResponseBody> call = quoraApi.getProfileByUserName(name);
        Response<ResponseBody> response = call.execute();
        if (response.isSuccessful() && response.body() != null) {
            return response.body().string();
        } else {
            return null;
        }
    }

    public List<GetAnswersRes.AnswerItem.Data> getAnswers(String qidx, Integer cursor, Integer count) throws Exception {

        GetAnswersReq req = GetAnswersReq.builder()
                .queryName("QuestionPagedListPaginationQuery")
                .variables(GetAnswersReq.Variables.builder()
                        .id(qidx)
                        .cursor(cursor)
                        .count(count)
                        .forceScoreVersion("ranking_toggle_upvote")
                        .build())
                .extensions(GetAnswersReq.Extensions.builder()
                        .hash(appProperties.getGetAnswersHash())
                        .build())
                .build();

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), JSON.toJSONString(req));
        Call<ResponseBody> call = quoraApi.getAnswers(appProperties.getQuoraFormKey(), appProperties.getQuoraCookie(), body);
        Response<ResponseBody> response = call.execute();
        if (response.isSuccessful() && response.body() != null) {

            String result = response.body().string();

            String[] rs = result.replaceAll("--qgqlmpb--", "").split("--qgqlmpb\r\nContent-Type: application/json;");

            if (rs.length > 0) {

                List<GetAnswersRes.AnswerItem.Data> data = Lists.newArrayList();

                List<String> rss = Lists.newArrayList();
                for (int i = 0; i < rs.length; i++) {
                    if (!Strings.isNullOrEmpty(rs[i])) {
                        rss.add(rs[i]);
                    }
                }

                for (int i = 0; i < rss.size(); i++) {

                    String r = rss.get(i);
                    if (r.startsWith("--qgqlmpb") || r.startsWith("Content-Type: application/json;")) {
                        continue;
                    }

                    if (i == 0) {
                        GetAnswersRes.AnswerFirst answerFirst = JSONObject.parseObject(r, GetAnswersRes.AnswerFirst.class);
                        if (answerFirst != null && answerFirst.getData() != null
                                && answerFirst.getData().getNode() != null && answerFirst.getData().getNode().getAnswers() != null
                                && answerFirst.getData().getNode().getAnswers().getEdges() != null) {
                            answerFirst.getData().getNode().getAnswers().getEdges().stream().filter(e -> e.getNode() != null).forEach(e -> {
                                if ("QuestionAnswerItem2".equals(e.getNode().getTypename())) {
                                    data.add(e);
                                }
                            });
                        }
                    } else {
                        GetAnswersRes.AnswerItem answerItem = JSONObject.parseObject(r, GetAnswersRes.AnswerItem.class);
                        if (answerItem.getData() != null && answerItem.getData().getNode() != null) {
                            if ("QuestionAnswerItem2".equals(answerItem.getData().getNode().getTypename())) {
                                data.add(answerItem.getData());
                            }
                        }
                    }
                }
                return data;
            }
        }
        return null;
    }

}
