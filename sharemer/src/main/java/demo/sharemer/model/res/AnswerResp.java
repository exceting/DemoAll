package demo.sharemer.model.res;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class AnswerResp {

    private Long aid;

    private String preview;

    @JSONField(name = "view_count")
    private Long viewCount;

    @JSONField(name = "like_count")
    private Long likeCount;

    @JSONField(name = "share_count")
    private Long shareCount;

    @JSONField(name = "word_count")
    private Long wordCount;

    @JSONField(name = "media_id")
    private String mediaId;

    private String color;

    private LocalDateTime ctime;

    private User user;

    private List<String> imgs;

    @JSONField(name = "img_count")
    private Integer imgCount = 0;

    @Setter
    @Getter
    public static class User {
        private Long id;
        private String name;
        private Long followers;
        private String avatar;
        private String credentials;
        @JSONField(name = "country_id")
        private Integer countryId;
        private String flag;
        @JSONField(name = "country_name")
        private String countryName;
    }
}
