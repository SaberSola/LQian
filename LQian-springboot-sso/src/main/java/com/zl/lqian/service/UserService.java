package com.zl.lqian.service;


import com.zl.lqian.entity.User;
import com.zl.lqian.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

@Service
public class UserService {

    @Autowired
    UserInfoMapper userMapper;

    @Autowired
    PlatformTransactionManager platformTransactionManager;

    public User getUserInfo(String userId){
        User user = userMapper.findUserById(userId);
        return user;
    }

}
