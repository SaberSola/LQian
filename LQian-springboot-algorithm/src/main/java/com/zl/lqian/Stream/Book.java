package com.zl.lqian.Stream;

public class Book {


    private String name;

    private String title;

    private String content;

    private Double price;

    public Book(String name,String title,String content,Double price){

        this.name = name;
        this.title = title;
        this.content = content;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
