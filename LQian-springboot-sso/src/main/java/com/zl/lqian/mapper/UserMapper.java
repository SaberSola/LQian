package com.zl.lqian.mapper;

import com.zl.lqian.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    public User findUserById(@Param("userId")String userId);
}
