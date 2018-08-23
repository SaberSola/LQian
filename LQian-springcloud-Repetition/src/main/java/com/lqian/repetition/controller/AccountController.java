package com.lqian.repetition.controller;

import com.alibaba.fastjson.JSONObject;
import com.lqian.repetition.Service.AccountService;
import com.lqian.repetition.conf.annotation.ConcurrentLimit;
import com.lqian.repetition.entity.AccountDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

    @Autowired
    private AccountService accountService;


    /**
     *
     * 模拟从库读
     * @param userId
     * @return
     */
    @RequestMapping(value = "/findByUserId",method = RequestMethod.POST)
    @ConcurrentLimit
    public AccountDO findByUserId(@RequestBody JSONObject jsonObject) {

        System.out.println(jsonObject.toJSONString());
        AccountDO accountDO = accountService.findByUserId(jsonObject.getString("userId"));

        return accountDO;
    }

}
