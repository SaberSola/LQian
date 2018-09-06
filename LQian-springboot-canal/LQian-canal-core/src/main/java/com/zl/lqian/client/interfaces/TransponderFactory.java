package com.zl.lqian.client.interfaces;

import com.alibaba.otter.canal.client.CanalConnector;
import com.zl.lqian.client.core.ListenerPoint;
import com.zl.lqian.config.ConfigProperties;

import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface TransponderFactory {


    MessageTransponder newTransponder(CanalConnector connector, Map.Entry<String, ConfigProperties.Instance> config,
                                      List<CanalEventListener> listeners, List<ListenerPoint> annoListeners);


}
