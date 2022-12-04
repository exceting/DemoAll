package demo.sharemer.model.http;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MediaGet extends Base {

    @JSONField(name = "video_url")
    private String videoUrl;

    @JSONField(name = "image_url")
    private String imageUrl;

}
