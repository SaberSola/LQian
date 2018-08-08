package com.zl.lqian.readwrite.controller;

import com.alibaba.fastjson.JSONObject;
import com.zl.lqian.readwrite.Service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1")
public class OrderController {


    @Autowired
    private OrderService orderService;

    /**
     *
     *  模拟主库写
     * @param jsonObject
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/orderPay",method = RequestMethod.POST)
    @ApiOperation(value = "添加操作")
    public String orderPay(@RequestBody JSONObject jsonObject) throws Exception{

        Integer count = jsonObject.getInteger("count");
        BigDecimal amount = jsonObject.getBigDecimal("amount");
        return orderService.orderPay(count, amount);

    }
}
