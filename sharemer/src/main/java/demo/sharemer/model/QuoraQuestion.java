package demo.sharemer.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class QuoraQuestion {

    /**
     * 问题id，跟quora保持一致
     */
    private Long id;

    /**
     * 加密后的id
     */
    private String idx;

    /**
     * 预留，父问题id
     */
    private Long parentId;

    /**
     * 所属主题
     */
    private Long topicId;

    /**
     * quora原问题标题
     */
    private String titleOri;

    /**
     * 问题译文
     */
    private String titleCn;

    /**
     * 原问题链接
     */
    private String url;

    /**
     * 回答数
     */
    private Integer answerCount;

    /**
     * -1删除；0正常
     */
    private Integer state;

    /**
     * 创建时间，跟quora保持一致
     */
    private LocalDateTime ctime;

    /**
     * 更新时间
     */
    private LocalDateTime mtime;

}