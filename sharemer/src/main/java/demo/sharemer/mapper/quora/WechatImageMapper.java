package demo.sharemer.mapper.quora;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import demo.sharemer.model.WechatImage;

import java.util.List;

@Mapper
public interface WechatImageMapper {

    void insert(@Param("pojo") WechatImage pojo);

    void update(@Param("pojo") WechatImage pojo);

    WechatImage one(@Param("id") int id);

    List<WechatImage> getImgsByAid(@Param("aid") Long aid,
                                   @Param("limit") Integer limit);

    Integer getImgCountByAid(@Param("aid") Long aid);

}