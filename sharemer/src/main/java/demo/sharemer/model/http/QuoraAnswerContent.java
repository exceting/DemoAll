package demo.sharemer.model.http;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class QuoraAnswerContent {

    private List<Section> sections;

    // 每个section视为一个段落
    @Setter
    @Getter
    public static class Section {
        private List<Span> spans;
        // 缩进
        private int indent;
        // 引用
        private boolean quoted;
        // image、plain、ordered-list(li-123)、unordered-list(li-点)、hyperlink-embed(卡片链接)、yt-embed(油管视频)、horizontal-rule（分割线）
        private String type;
        @JSONField(name = "is_rtl")
        private boolean isRtl;

        @Setter
        @Getter
        public static class Span {

            private String text;
            private Modifiers modifiers;

            @Setter
            @Getter
            public static class Modifiers {
                // 是否是斜体
                private boolean italic;
                // 是否加粗
                private boolean bold;
                // 插图
                private String image;
                // 超链接
                private Link link;
                // embed
                private Embed embed;

                @Setter
                @Getter
                public static class Link {
                    private String type;
                    private String url;
                }

                @Setter
                @Getter
                public static class Embed {
                    private String url;
                    private String title;
                    private String snippet;
                    @JSONField(name = "image_url")
                    private String imageUrl;
                    private String type;
                    @JSONField(name = "quora_content_type")
                    private String quoraContentType;
                    @JSONField(name = "quora_oid")
                    private String quoraOid;
                }
            }
        }
    }
}
