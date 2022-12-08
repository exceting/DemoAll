package demo.sharemer.controller;

import com.google.common.collect.Lists;
import demo.sharemer.mapper.quora.QuoraAnswerMapper;
import demo.sharemer.model.QuoraAnswer;
import demo.sharemer.model.res.AnswerResp;
import demo.sharemer.service.QuoraProcessor;
import demo.sharemer.utils.BaseResponse;
import demo.sharemer.utils.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RequestMapping("/quora")
@RestController
public class QuoraController {

    @Resource
    private QuoraProcessor quoraProcessor;

    @Resource
    private QuoraAnswerMapper quoraAnswerMapper;

    @GetMapping("/get_answers_by_qid")
    public BaseResponse<Page<AnswerResp>> getAnswersByQid(@RequestParam Long qid,
                                                          @RequestParam(required = false, defaultValue = "1") Integer pn,
                                                          @RequestParam(required = false, defaultValue = "10") Integer ps) {

        return BaseResponse.success(quoraProcessor.getAnswersByQid(qid, pn, ps));
    }

    @GetMapping("/get_answer_detail_by_aid")
    public BaseResponse<String> getAnswerByAid(@RequestParam Long aid,
                                               @RequestParam(required = false, defaultValue = "0") Integer type) {
        QuoraAnswer answer = quoraAnswerMapper.one(aid);
        if (answer != null) {
            return BaseResponse.success(type == 0 ? answer.getContentCn() : answer.getContentOri());
        }
        return BaseResponse.success(null);
    }

    @GetMapping("/export_to_wechat")
    public BaseResponse<String> exportToWechat(@RequestParam Long qid, @RequestParam Long aid) throws Exception {
        quoraProcessor.exportToWechat(qid, aid);
        return BaseResponse.success("success!");
    }

    @GetMapping("/exports_to_wechat")
    public BaseResponse<String> exportsToWechat(@RequestParam Long qid, @RequestParam String aids) throws Exception {
        String[] aidArray = aids.split(",");
        List<Long> aidList = Lists.newArrayList();
        for (String aid : aidArray) {
            aidList.add(Long.parseLong(aid));
        }
        quoraProcessor.exportToWechat(qid, aidList);
        return BaseResponse.success("success!");
    }
}
