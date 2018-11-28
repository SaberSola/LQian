package com.zl.lqian.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Person implements Serializable {

    private int id;

    private String name;

    private String Date;

    private String note;
}
