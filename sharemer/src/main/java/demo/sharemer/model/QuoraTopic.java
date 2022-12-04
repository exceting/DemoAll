package demo.sharemer.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class QuoraTopic {

    /**
     * 话题id，自增
     */
    private Long id;

    /**
     * quora原话题标题
     */
    private String titleOri;

    /**
     * 话题译文
     */
    private String titleCn;

    /**
     * 话题封面
     */
    private String cover;

    /**
     * 原话题链接
     */
    private String url;

    /**
     * 问题数
     */
    private Integer questionCount;

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