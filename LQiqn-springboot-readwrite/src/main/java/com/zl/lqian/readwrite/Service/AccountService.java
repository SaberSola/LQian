package com.zl.lqian.readwrite.Service;


import com.zl.lqian.readwrite.conf.annotation.RateLimit;
import com.zl.lqian.readwrite.conf.annotation.SlaveDataSource;
import com.zl.lqian.readwrite.entity.AccountDO;
import com.zl.lqian.readwrite.mapper.AccountMapper;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);



    @SlaveDataSource
    @RateLimit(key = "zhanglei",context = "zl")
    public AccountDO findByUserId(String userId) {
        final AccountDO accountDO = accountMapper.findByUserId(userId);
        LOGGER.debug("异步测试结果为----------------》" +accountDO.getBalance());
        return accountDO;
    }

}
