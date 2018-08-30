package com.lqian.design.proxy;

public class PrinterProxy implements Printable{

    private String name;            // 名字、
    private Printer real;           // “本人”‘

    //构造函数
    public PrinterProxy() {
    }
    public PrinterProxy(String name) {      // 构造函数
        this.name = name;
    }

    @Override
    public void setPrinterName(String name) {

        if (real != null){
            real.setPrinterName(name);
        }
        this.name = name;
    }

    @Override
    public String getPrinterName() {
        return name;
    }

    @Override
    public void print(String string) {
        realize();
        real.print(string);
    }
    

    private synchronized void realize() {   // 生成“本人”
        if (real == null) {
            real = new Printer(name);
        }
    }
}
