package demo.we.media.task;

import demo.we.media.api.OfficalLanguageService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.function.Supplier;

/**
 * 获取并解析外交部页面，分析出每个国家的官方语言
 */
@Component
public class OfficalLanguageProcessor implements Processor<String> {

    @Resource
    private OfficalLanguageService officalLanguageService;

    @Override
    public void process(Supplier<String> params) {
        try {
            String html = officalLanguageService.getCountries(params.get());
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
