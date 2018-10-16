package com.zl.lqian.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class SeckillMessage implements Serializable {

    private long userId;

    private long goodsId;
}
