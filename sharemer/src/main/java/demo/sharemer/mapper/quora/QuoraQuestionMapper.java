package demo.sharemer.mapper.quora;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import demo.sharemer.model.QuoraQuestion;

@Mapper
public interface QuoraQuestionMapper {

    void insert(@Param("pojo") QuoraQuestion pojo);

    void update(@Param("pojo") QuoraQuestion pojo);

    QuoraQuestion one(@Param("id") Long id);

}