package demo.we.media.controller;

import demo.we.media.task.Processor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class LanguageController {

    @Resource
    private Processor<String> officalLanguageProcessor;

    @GetMapping("get_countries")
    public String getCountries(@RequestParam(name = "area_id") String areaId) throws Exception {
        officalLanguageProcessor.process(()-> areaId);
        return "success!";
    }

}
