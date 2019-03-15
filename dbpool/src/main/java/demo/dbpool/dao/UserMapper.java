/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2018 All Rights Reserved.
 */
package demo.dbpool.dao;

import demo.dbpool.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author sunqinwen
 * @version \: BadgeMapper.java,v 0.1 2018-09-24 12:10
 */
@Mapper
public interface UserMapper {

    void insert(@Param("user") User user);

    User one(@Param("id") Integer id);

}
