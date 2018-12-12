package com.zl.lqian.Observer;

/**
 * @Author zl
 * @Date 2018-12-12
 * @Des ${todo}
 */
public class Striker implements ObserverMode<PositionAndStrategy>{

    @Override
    public void update(PositionAndStrategy positionAndStrategy) {
        System.out.println("前锋：" + positionAndStrategy.getStrikerStrategy() );
    }
}
