package com.zl.lqian.mapper;

import com.zl.lqian.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {

    public User findUserById(@Param("userId")String userId);
}
