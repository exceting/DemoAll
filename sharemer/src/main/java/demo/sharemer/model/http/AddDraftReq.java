package demo.sharemer.model.http;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AddDraftReq {

    private List<Article> articles;

    @Setter
    @Getter
    public static class Article {

        private String title;

        private String author;

        private String content;

        @JSONField(name = "content_source_url")
        private String contentSourceUrl;

        @JSONField(name = "thumb_media_id")
        private String thumbMediaId;

        @JSONField(name = "need_open_comment")
        private int needOpenComment;

        @JSONField(name = "only_fans_can_comment")
        private int onlyFansCanComment;
    }
}
