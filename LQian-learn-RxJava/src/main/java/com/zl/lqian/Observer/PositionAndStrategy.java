package com.zl.lqian.Observer;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @Author zl
 * @Date 2018-12-12
 * @Des 被观察者实现类
 */
public class PositionAndStrategy implements Subject<PositionAndStrategy> {


    private ArrayList<ObserverMode<PositionAndStrategy>> observers;  //观察者注册信息列表
    private String strikerStrategy;     //前锋战术指令
    private String defenderStrategy;    //后卫战术指令

    public PositionAndStrategy() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(ObserverMode<PositionAndStrategy> observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(ObserverMode observer) {
        this.observers = this.observers.stream()
                .filter(observer1 -> !observer1.equals(observer))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void notifyObservers() {
        this.observers.forEach(observer -> observer.update(this));
    }

    public void setStrategy(String strikerStrategy, String defenderStrategy) {
        this.strikerStrategy = strikerStrategy;
        this.defenderStrategy = defenderStrategy;
        notifyObservers();
    }

    public String getStrikerStrategy() {
        return strikerStrategy;
    }

    public String getDefenderStrategy() {
        return defenderStrategy;
    }
}
