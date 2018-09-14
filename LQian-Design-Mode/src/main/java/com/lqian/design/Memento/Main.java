package com.lqian.design.Memento;

public class Main {
    public static void main(String[] args){

        Gamer gamer = new Gamer(100);               // 最初的所持金钱数为100
        Memento memento = gamer.createMemento();    // 保存最初的状态

        for (int i = 0; i < 10; i ++){
            System.out.println("==== " + i);        // 显示掷骰子的次数
            System.out.println("当前状态:" + gamer);    // 显示主人公现在的状态

            gamer.bet();    // 进行游戏

            System.out.println("所持金钱为" + gamer.getMoney() + "元。");

            //如何处理bet
            if (gamer.getMoney() > memento.getMoney()){
                //钱增加了
                System.out.println("    （所持金钱增加了许多，因此保存游戏当前的状态）");
                memento = gamer.createMemento();
            }else if (gamer.getMoney() < memento.getMoney()){
                System.out.println("    （所持金钱减少了许多，因此将游戏恢复至以前的状态）");
               gamer.restoreMemento(memento);
            }
            // 等待一段时间
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println("");
        }
    }
}
