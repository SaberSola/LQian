package com.lqian.design.proxy;

public class Printer implements Printable {


    private String name;

    private Printer(){
        heavyJob("正在生成Printer的实例");
    }
    public Printer(String name) {                   // 构造函数
        this.name = name;
        heavyJob("正在生成Printer的实例(" + name + ")");
    }
    @Override
    public void setPrinterName(String name) {
        this.name = name;
    }

    @Override
    public String getPrinterName() {
        return name;
    }

    @Override
    public void print(String string) {
        System.out.println("=== " + name + " ===");
        System.out.println(string);
    }

    private void heavyJob(String msg){
        System.out.print(msg);
        for (int i = 0; i < 5; i ++ ){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.print(".");
        }
        System.out.print("结束");
    }
}
