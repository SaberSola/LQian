package com.zl.lqian.StandardMBean.domain;

public interface HelloMBean {
    public String getName();
    public void setName(String name);
    public void printHello();
    public void printHello(String whoName);
}
