package demo.sharemer.model.http;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class GetAnswersRes {

    @Data
    public static class AnswerFirst {
        private Data data;

        @lombok.Data
        public static class Data {
            private Node node;

            @lombok.Data
            public static class Node {
                @JSONField(name = "__typename")
                private String typename;
                private Answers answers;

                @lombok.Data
                public static class Answers {
                    private List<AnswerItem.Data> edges;
                }
            }
        }
    }

    @Data
    public static class AnswerItem {
        private Data data;

        @lombok.Data
        public static class Data {
            private String id;
            private int cursor;
            private Node node;

            @lombok.Data
            public static class Node {
                @JSONField(name = "__typename")
                private String typename;
                private Question question;
                private Answer answer;

                @lombok.Data
                public static class Question {
                    private long qid;
                    private String id;
                }

                @lombok.Data
                public static class Answer {
                    private long aid;
                    private String id;
                    private String content;
                    private String questionPageImpressionHash;
                    private int numDisplayComments; // 评论数
                    private int numUpvotes; // 点赞数
                    private int numSharers; // 分享数
                    private int numViews; // 浏览量
                    private Author author;
                    private Question question;
                    private AuthorCredential authorCredential;
                    private OriginalQuestionIfDifferent originalQuestionIfDifferent; // 有些回答是关联自其它问题的，通过这个字段可以理清
                    private long creationTime; // 回答时间
                    private String url; // 回答地址

                    @lombok.Data
                    public static class Author {
                        private String id;
                        private long uid;
                        private boolean hasCredentials;
                        private List<Name> names;
                        private String profileUrl; // 链接（重名的会有编号，这是唯一的）
                        private int followerCount; // 粉丝量
                        private String profileImageUrl; // 高清头像

                        @lombok.Data
                        public static class Name {
                            private String givenName;
                            private String familyName;
                        }
                    }

                    @lombok.Data
                    public static class Question {
                        private String title;
                        private String url;
                    }

                    @lombok.Data
                    public static class AuthorCredential {
                        private String description;
                    }

                    @lombok.Data
                    public static class OriginalQuestionIfDifferent {

                        private Question question;

                        @lombok.Data
                        public static class Question {
                            private String id;
                            private long qid;
                            private String title;
                            private String url;
                        }
                    }
                }
            }
        }
    }
}
