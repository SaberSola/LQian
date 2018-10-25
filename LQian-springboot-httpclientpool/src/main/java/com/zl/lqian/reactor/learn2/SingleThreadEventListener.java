package com.zl.lqian.reactor.learn2;

import java.util.List;

public interface SingleThreadEventListener<T> {

    public void onDataChunk(List<String> chunk);

    public void processComplete();

    public void processError(Throwable e);
}
