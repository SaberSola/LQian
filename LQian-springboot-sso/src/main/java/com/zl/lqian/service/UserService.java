package com.zl.lqian.service;


import com.zl.lqian.entity.User;
import com.zl.lqian.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User getUserInfo(String userId){
        User user = userMapper.findUserById(userId);
        return user;
    }

}
