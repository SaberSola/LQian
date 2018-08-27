package com.CompletableFutureDemo.domain;

public class Persion {

    private String name;

    private String sex;

    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    public  Persion(String name,String sex,String age){
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
}
