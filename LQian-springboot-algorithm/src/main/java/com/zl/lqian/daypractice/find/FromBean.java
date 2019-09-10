package com.zl.lqian.daypractice.find;


import java.util.List;

/**
 * @Author zl
 * @Date 2019-09-10
 * @Des ${todo}
 */
public class FromBean {

    private  String name;
    private  int id;
    private  List<String> list;

    public int getId() {
        return id;
    }

    public FromBean(){}
    public void setId(int id) {
        this.id = id;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
