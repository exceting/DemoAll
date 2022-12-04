package demo.sharemer.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class QuoraAnswer {

    /**
     * 回答id，跟quora保持一致
     */
    private Long id;

    /**
     * 加密后的id
     */
    private String idx;

    /**
     * 所属问题id
     */
    private Long questionId;

    /**
     * 答主id
     */
    private Long userId;

    /**
     * 预览
     */
    private String preview;

    /**
     * 原文
     */
    private String contentOri;

    /**
     * 译文
     */
    private String contentCn;

    /**
     * 原回答链接
     */
    private String url;

    /**
     * 浏览数
     */
    private Integer viewCount;

    /**
     * 点赞数
     */
    private Integer likeCount;

    /**
     * 分享数
     */
    private Integer shareCount;

    /**
     * 字数
     */
    private Integer wordCount;

    /**
     * 是否包含图片，0否1是
     */
    private Integer haveImg;

    /**
     * -1负面；0中立；1正面
     */
    private Integer mood;

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