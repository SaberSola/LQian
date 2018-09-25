package com.abstractDemo;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseMessage implements Serializable {

    private String content;

    private String name;
}
