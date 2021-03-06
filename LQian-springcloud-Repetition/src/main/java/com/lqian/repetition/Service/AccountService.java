package com.lqian.repetition.Service;


import com.lqian.repetition.conf.annotation.ConcurrentLimit;
import com.lqian.repetition.entity.AccountDO;
import com.lqian.repetition.mapper.AccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);



    public AccountDO findByUserId(String userId) {
        final AccountDO accountDO = accountMapper.findByUserId(userId);
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        LOGGER.debug("异步测试结果为----------------》" +accountDO.getBalance());
        return accountDO;
    }

    public static void main(String[] args) {
        Date localDateTime = new Date();
        System.out.println(localDateTime);

    }

}
