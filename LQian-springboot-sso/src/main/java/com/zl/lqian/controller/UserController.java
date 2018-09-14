package com.zl.lqian.controller;


import com.alibaba.fastjson.JSONObject;
import com.zl.lqian.entity.ResponseDTO;
import com.zl.lqian.entity.User;
import com.zl.lqian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController("/api/v1")
public class UserController {


    @Autowired
    UserService userService;


    @RequestMapping(value = "/findUserInfo", method = RequestMethod.GET)
    @PreAuthorize("hasRole('findUserInfo')")
    public ResponseDTO getUserInfo(@RequestParam String userId) {
        ResponseDTO responseDTO = new ResponseDTO();
        User user = userService.getUserInfo(userId);
        responseDTO.setData(user);
        return  responseDTO;
    }
}
