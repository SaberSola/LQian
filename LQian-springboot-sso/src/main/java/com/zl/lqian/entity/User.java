package com.zl.lqian.entity;

import lombok.Data;

import java.util.Date;


@Data
public class User {

    private Integer id;

    private String userId;

    private String userName;

    private String password;

    private Integer authType;

    private String hashKey;

    private Date lastLoginTime;

}
