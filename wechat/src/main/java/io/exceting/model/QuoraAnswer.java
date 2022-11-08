package io.exceting.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class QuoraAnswer {

    private List<Section> sections;

    @Setter
    @Getter
    public static class Section {
        private List<Span> spans;
        // 缩进
        private int indent;
        // 是否是引用部分
        private boolean quoted;
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
                // 插图
                private String image;
            }
        }
    }
}
