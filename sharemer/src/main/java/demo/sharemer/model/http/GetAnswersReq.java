package demo.sharemer.model.http;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAnswersReq {

    private String queryName;

    private Variables variables;

    private Extensions extensions;

    @Data
    @Builder
    public static class Variables {
        private String id;
        private Integer count;
        private Integer cursor;
        private String forceScoreVersion;
    }

    @Data
    @Builder
    public static class Extensions {
        private String hash;
    }

}