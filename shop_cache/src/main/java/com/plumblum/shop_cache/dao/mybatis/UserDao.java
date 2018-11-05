package com.plumblum.shop_cache.dao.mybatis;

import com.plumblum.shop_cache.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Auther: cpb
 * @Date: 2018/11/5 09:52
 * @Description:
 */
@Mapper
public interface UserDao {
    @Select("select * from sys_user where id = #{id}")
    public User getOne(String id);

}
