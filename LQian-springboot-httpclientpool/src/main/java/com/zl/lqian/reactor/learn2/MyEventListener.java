package com.zl.lqian.reactor.learn2;

import java.util.List;

public interface  MyEventListener<T> {

    void onDataChunk(List<T> chunk);

    void processComplete();
}
