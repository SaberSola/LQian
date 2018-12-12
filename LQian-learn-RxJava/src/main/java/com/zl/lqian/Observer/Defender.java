package com.zl.lqian.Observer;

/**
 * @Author zl
 * @Date 2018-12-12
 * @Des ${todo}
 */
public class Defender implements ObserverMode<PositionAndStrategy>{

    @Override
    public void update(PositionAndStrategy positionAndStrategy) {
        System.out.println("后卫：" + positionAndStrategy.getDefenderStrategy() );
    }
}
