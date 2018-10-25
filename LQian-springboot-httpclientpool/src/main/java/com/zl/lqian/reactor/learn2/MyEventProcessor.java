package com.zl.lqian.reactor.learn2;

public interface MyEventProcessor {

    void register(MyEventListener<String> eventListener);

    void dataChunk(String... values);

    void processComplete();

    void register(SingleThreadEventListener<String> eventListener);
}
