package demo.sharemer.mapper.quora;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import demo.sharemer.model.QuoraUser;

@Mapper
public interface QuoraUserMapper {

    void insert(@Param("pojo") QuoraUser pojo);

    void update(@Param("pojo") QuoraUser pojo);

    QuoraUser one(@Param("id") Long id);

}