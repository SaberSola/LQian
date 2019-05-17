package com.zl.lqian.waitornotify;

/**
 * @Author zl
 * @Date 2019-05-17
 * @Des ${todo}
 */
public class WaitNotifyObject {


    public void wakeup() {
        synchronized (this) {
            this.notify();
        }
    }

    public void wakeUpAll(){
        synchronized (this) {
            this.notifyAll();
        }
    }

    public void await(){
        synchronized (this){
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }

    }
}
