package demo.sharemer.mapper.quora;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import demo.sharemer.model.QuoraTopic;

@Mapper
public interface QuoraTopicMapper {

    void insert(@Param("pojo") QuoraTopic pojo);

    void update(@Param("pojo") QuoraTopic pojo);

    QuoraTopic one(@Param("id") int id);

}