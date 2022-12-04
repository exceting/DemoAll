package demo.sharemer.mapper.quora;

import demo.sharemer.model.res.AnswerResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import demo.sharemer.model.QuoraAnswer;

import java.util.List;

@Mapper
public interface QuoraAnswerMapper {

    void insert(@Param("pojo") QuoraAnswer pojo);

    void update(@Param("pojo") QuoraAnswer pojo);

    QuoraAnswer one(@Param("id") Long id);

    Integer getAnswersByQidCount(@Param("qid") Long qid);

    Integer getAnswersByQidAndLC(@Param("qid") Long qid,
                                 @Param("lc") Integer lc);

    List<AnswerResp> getAnswersByQid(@Param("qid") Long qid,
                                     @Param("offset") Integer offset,
                                     @Param("ps") Integer ps);

}