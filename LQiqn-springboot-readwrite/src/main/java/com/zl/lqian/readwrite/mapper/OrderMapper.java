package com.zl.lqian.readwrite.mapper;

import com.zl.lqian.readwrite.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;


@Component
public interface OrderMapper {
    @Insert(" insert into `order` (create_time,number,status,product_id,total_amount,count,user_id) " +
            " values ( #{createTime},#{number},#{status},#{productId},#{totalAmount},#{count},#{userId})")
    int save(Order order);
}
