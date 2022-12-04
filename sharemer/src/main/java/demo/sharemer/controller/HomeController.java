package demo.sharemer.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import demo.sharemer.api.QuoraService;
import demo.sharemer.job.QuoraToDBJob;
import demo.sharemer.model.http.GetAnswersRes;
import demo.sharemer.model.http.QuoraAnswerContent;
import demo.sharemer.utils.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author sunqinwen
 * @version \: HomeController.java,v 0.1 2022-11-20 23:30
 */
@Slf4j
@RestController
public class HomeController {

    @Resource
    private QuoraService quoraService;

    @Resource
    private QuoraToDBJob quoraToDBJob;

    @RequestMapping("/")
    public ModelAndView home() {
        return new ModelAndView("home");
    }

    @GetMapping("/page")
    public ModelAndView page(@RequestParam String qidx,
                             @RequestParam(required = false) Integer cursor,
                             @RequestParam Integer count,
                             Model model) throws Exception {

        List<GetAnswersRes.AnswerItem.Data> dataList = quoraService.getAnswers(qidx, cursor, count);

        List<String> rs = Lists.newArrayList();
        for (GetAnswersRes.AnswerItem.Data d : dataList) {
            String content = d.getNode().getAnswer().getContent();
            if (Strings.isNullOrEmpty(content)) {
                throw new IllegalStateException("回答为空！answer = " + JSON.toJSONString(d));
            }
            // content = StringEscapeUtils.unescapeJava(content);
            QuoraAnswerContent answerContent = JSONObject.parseObject(content, QuoraAnswerContent.class);

            StringBuilder ensb = new StringBuilder();
            quoraToDBJob.toHtml(ensb, null, null, answerContent, null);
            rs.add(ensb.toString());
        }

        StringBuilder result = new StringBuilder();

        if (rs.size() == 1) {
            rs.forEach(result::append);
        } else {
            rs.forEach(r -> result.append(r).append("<br/><hr/><br/>"));
        }


        Map<String, String> r = Maps.newHashMap();
        r.put("title", "哈哈哈哈");
        r.put("content", result.toString().replaceAll("\n", "</br>"));
        model.addAttribute("rmap", r);

        return new ModelAndView("page");
    }

    @GetMapping("/get_all_answers")
    public BaseResponse<List<GetAnswersRes.AnswerItem.Data>> getAllAnswersByQid(@RequestParam String qidx,
                                                                                @RequestParam int cursor,
                                                                                @RequestParam int count) throws Exception {
        return BaseResponse.success(quoraService.getAnswers(qidx, cursor, count));
    }

    @GetMapping("/t")
    public BaseResponse<String> uploadToQiniu(@RequestParam String idx,
                                              @RequestParam(required = false) Integer cursor,
                                              @RequestParam Integer count) throws Exception {
        quoraToDBJob.process(idx, cursor, count);
        return BaseResponse.success("已完成！");
    }
}
