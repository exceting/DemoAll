package demo.sharemer.model.http;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MediaUpload extends Base {
    private String url;
    @JSONField(name = "media_id")
    private String mediaId;
}
