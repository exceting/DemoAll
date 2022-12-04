package demo.sharemer.mapper.quora;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import demo.sharemer.model.WechatTask;

import java.util.List;

@Mapper
public interface WechatTaskMapper {

    void insert(@Param("pojo") WechatTask pojo);

    void update(@Param("pojo") WechatTask pojo);

    WechatTask one(@Param("id") int id);

    WechatTask getByAid(@Param("aid") Long aid);

    List<WechatTask> getByParentId(@Param("parentId") Long parentId);

}