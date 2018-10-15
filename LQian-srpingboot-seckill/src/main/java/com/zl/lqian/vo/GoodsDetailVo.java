package com.zl.lqian.vo;

import com.zl.lqian.bean.User;
import lombok.Data;

@Data
public class GoodsDetailVo {

    private int seckillStatus = 0;

    private int remainSeconds = 0;

    private GoodsVo goods ;

    private User user;
}
