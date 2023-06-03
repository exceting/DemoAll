package demo.we.media.controller;

import demo.we.media.api.OfficalLanguageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class LanguageController {

    @Resource
    private OfficalLanguageService officalLanguageService;

    @GetMapping("get_countries")
    public String getCountries(@RequestParam(name = "area_id") String areaId) throws Exception {
        return officalLanguageService.getCountries(areaId);
    }

}
