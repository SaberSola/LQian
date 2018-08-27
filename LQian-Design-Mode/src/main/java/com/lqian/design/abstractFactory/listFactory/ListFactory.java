package com.lqian.design.abstractFactory.listFactory;

import com.lqian.design.abstractFactory.factory.Factory;
import com.lqian.design.abstractFactory.factory.Link;
import com.lqian.design.abstractFactory.factory.Page;
import com.lqian.design.abstractFactory.factory.Tray;

public  class ListFactory  extends Factory{

    public Link createLink(String caption, String url) {
        return new ListLink(caption, url);
    }
    public Tray createTray(String caption) {
        return new ListTray(caption);
    }
    public Page createPage(String title, String author) {
        return new ListPage(title, author);
    }
}
