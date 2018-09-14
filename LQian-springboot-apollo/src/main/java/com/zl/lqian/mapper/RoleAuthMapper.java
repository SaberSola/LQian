package com.zl.lqian.mapper;

import com.zl.lqian.entity.RoleAuth;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface RoleAuthMapper {

    List<RoleAuth> findByRole_id(@Param("roleId") Integer roleId);
}
