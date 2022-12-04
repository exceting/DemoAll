package demo.sharemer.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class QuoraUser {

    /**
     * 答主id，跟quora保持一致
     */
    private Long id;

    /**
     * 加密后的id
     */
    private String idx;

    /**
     * 答主链接
     */
    private String url;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 昵称
     */
    private String name;

    /**
     * 粉丝量
     */
    private Integer followers;

    /**
     * 教育信息
     */
    private String edu;

    /**
     * 坐标
     */
    private String live;

    /**
     * 坐标所属国家
     */
    private Integer countryId;

    /**
     * 认证信息
     */
    private String credentials;

    /**
     * 0保密；1男；2女
     */
    private Integer gender;

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