package com.zl.lqian.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRoles implements Serializable {


    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer user_id;

    private Integer role_id;

    private Integer status;

    private Integer is_deleted;
}
