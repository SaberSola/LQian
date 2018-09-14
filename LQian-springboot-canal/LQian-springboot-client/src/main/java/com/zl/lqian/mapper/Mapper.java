package com.zl.lqian.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 数据防问层
 */
@Repository
public interface Mapper {
	@Insert("${sql}")
	void doOption(@Param("sql") String sql);
}
