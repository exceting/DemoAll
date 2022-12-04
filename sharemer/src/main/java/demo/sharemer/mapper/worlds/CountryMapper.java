package demo.sharemer.mapper.worlds;

import demo.sharemer.model.Country;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CountryMapper {

    List<Country> getCountries(@Param("in_or_notin") String inOrNotIn);

    void updateIcon(@Param("id") int id,
                    @Param("icon") String icon);

    List<Country> getStates(@Param("in_or_notin") String inOrNotIn);

    List<Country> getCities(@Param("in_or_notin") String inOrNotIn);

}
