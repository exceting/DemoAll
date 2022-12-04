package demo.sharemer.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class WechatTask {

    /**
     * 话题id，自增
     */
    private Long id;

    /**
     * 草稿mediaId，任务完成时才有
     */
    private String mediaId;

    /**
     * 草稿url
     */
    private String url;

    /**
     * 所属问题id
     */
    private Long questionId;

    /**
     * 父任务id，0为根任务
     */
    private Long parentId;

    /**
     * 回答id
     */
    private Long answerId;

    /**
     * 任务颜色
     */
    private String color;

    /**
     * -1删除；0待执行；1已执行
     */
    private Integer state;

    /**
     * 创建时间
     */
    private LocalDateTime ctime;

    /**
     * 更新时间
     */
    private LocalDateTime mtime;

}