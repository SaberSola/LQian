package com.zl.lqian.mapper;

import com.zl.lqian.entity.UserRoles;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRolesMapper {


    public List<UserRoles> findByUserId(@Param("userId") Integer userId);
}
