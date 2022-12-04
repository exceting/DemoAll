package demo.sharemer.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class WechatImage {

    /**
     * 话题id，自增
     */
    private Long id;

    /**
     * mediaId
     */
    private String mediaId;

    /**
     * 微信图库url
     */
    private String wechatUrl;

    /**
     * 七牛图库url
     */
    private String qiniuUrl;

    /**
     * 图片原url
     */
    private String otherUrl;

    /**
     * 所属业务id（如问题id、回答id）
     */
    private Long oid;

    /**
     * 所属业务类型：0Quora回答插图，1Quora回答封面
     */
    private Integer otype;

    /**
     * 宽
     */
    private Integer width;

    /**
     * 高
     */
    private Integer height;

    /**
     * -1删除；0正常
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