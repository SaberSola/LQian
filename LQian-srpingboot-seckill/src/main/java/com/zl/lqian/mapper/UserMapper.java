package com.zl.lqian.mapper;

import com.zl.lqian.bean.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {

    @Select("select * from sk_user where id = #{id}")
    public UserMapper getById(@Param("id") long id);

    @Update("update sk_user set password = #{password} where id = #{id}")
    public void update(User toBeUpdate);
}
