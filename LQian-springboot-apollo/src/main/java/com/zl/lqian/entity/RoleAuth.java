package com.zl.lqian.entity;

import lombok.Data;

@Data
public class RoleAuth {

    private Integer id;
    //角色id
    private Integer role_id;

    //系统id
    private Integer system_id;

    //模块id
    private Integer module_id;

    //方法id
    private Integer action_id;

    //状态
    private Integer status;
}
