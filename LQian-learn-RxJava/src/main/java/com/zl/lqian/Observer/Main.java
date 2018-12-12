package com.zl.lqian.Observer;

/**
 * @Author zl
 * @Date 2018-12-12
 * @Des ${todo}
 */
public class Main {


    public static void main(String[] args){

        //创建战术板（主题类）
        PositionAndStrategy positionAndStrategy = new PositionAndStrategy();

        //创建前锋和后卫（观察者）
        Striker striker = new Striker();
        Defender defender = new Defender();

        //在主题中注册观察者
        positionAndStrategy.registerObserver(striker);
        positionAndStrategy.registerObserver(defender);
        //主题发生变化
        System.out.println("主题第一次变化！");
        positionAndStrategy.setStrategy("进攻", "防守");

        //现在后卫距离太远，听不到教练的指令了（删除观察者）
        positionAndStrategy.removeObserver(defender);

        //主题发生变化
        System.out.println("\n主题第二次变化");
        positionAndStrategy.setStrategy("继续进攻", "压上支援");

        //后卫现在又能听到指令了
        positionAndStrategy.registerObserver(defender);
        //主题发生变化
        System.out.println("\n主题第三次变化");
        positionAndStrategy.setStrategy("表现的不错，保持住", "后卫你们在干什么，叫你们压上支援");
    }
}
