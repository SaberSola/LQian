package com.zl.lqian.readwrite.controller;


import com.zl.lqian.readwrite.Service.AccountService;
import com.zl.lqian.readwrite.entity.AccountDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping("/findByUserId")
    public AccountDO findByUserId(@RequestParam("userId") String userId) {

        AccountDO accountDO = accountService.findByUserId(userId);

        return accountDO;
    }

}
