/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.websocket.mapper;

import demo.websocket.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author sunqinwen
 * @version \: UserMapper.java,v 0.1 2019-03-01 15:22
 */
@Mapper
public interface UserMapper {

    void insert(@Param("pojo") User user);

    User one(@Param("id") Integer id);

}
