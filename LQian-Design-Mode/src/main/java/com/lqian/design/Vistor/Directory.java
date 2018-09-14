package com.lqian.design.Vistor;


import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;



public class Directory extends Entry {

    private String name;

    private List dir = new CopyOnWriteArrayList();

    public Directory(String name) {         // 构造函数
        this.name = name;
    }

    public String getName() {               // 获取名字
        return name;
    }

    public int getSize() {                  // 获取大小
        int size = 0;
        Iterator it = dir.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry)it.next();
            size += entry.getSize();
        }
        return size;
    }

    public Entry add(Entry entry) {         // 增加目录条目
        dir.add(entry);
        return this;
    }

    public Iterator iterator() {      // 生成Iterator
        return dir.iterator();
    }

    public void accept(Visitor v) {         // 接受访问者的访问
        v.visit(this);
    }
}
