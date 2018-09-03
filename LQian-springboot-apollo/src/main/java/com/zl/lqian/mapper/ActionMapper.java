package com.zl.lqian.mapper;

import com.zl.lqian.entity.Action;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ActionMapper {
    List<Action> findByIdList(@Param("idList") List<Integer> idList);
}
