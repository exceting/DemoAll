package io.exceting.processors;

import com.alibaba.fastjson.JSONObject;
import io.exceting.api.QuoraService;
import io.exceting.model.QuoraAnswer;
import org.apache.commons.text.StringEscapeUtils;

public class QuoraProcessor {

    public static final QuoraProcessor INSTANT = new QuoraProcessor();

    private QuoraProcessor() {
    }

    public QuoraAnswer getAnswerByUrl(String qid, String aid) throws Exception {
        String body = QuoraService.INSTANT.getAnswer(qid, aid);
        body = body.substring(body.indexOf("{\\\"data\\\":{\\\"answer"));
        body = StringEscapeUtils.unescapeJava(body.substring(0, body.indexOf("}}\");") + 2));

        JSONObject jsonObject = JSONObject.parseObject(body);
        return JSONObject.parseObject(jsonObject.getJSONObject("data").getJSONObject("answer").getJSONObject("content").toJSONString(), QuoraAnswer.class);
    }

}
