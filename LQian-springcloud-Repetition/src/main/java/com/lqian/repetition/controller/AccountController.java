package com.lqian.repetition.controller;

import com.alibaba.fastjson.JSONObject;
import com.lqian.repetition.Service.AccountService;
import com.lqian.repetition.conf.annotation.ConcurrentLimit;
import com.lqian.repetition.entity.AccountDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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
    @RequestMapping(value = "/findByUserId",method = RequestMethod.GET)
    @ConcurrentLimit
    public AccountDO findByUserId(@RequestParam String userId) {
        AccountDO accountDO = accountService.findByUserId(userId);

        return accountDO;
    }

    @RequestMapping(value = "/importExcel",method = RequestMethod.GET)
    public  AccountDO importExcel(HttpServletResponse response) {


        // accountService.exportExcel(response);
        AccountDO accountDO =  accountService.findByUserId("123");
        return accountDO;
    }



}
