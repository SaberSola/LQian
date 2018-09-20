package com.zl.lqian.ManagementFactory;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class GarbageCollectorInfo {

    private long m_lastGcCount = 0;

    private long m_lastGcTime = 0;

    private long m_lastFullgcTime = 0;

    private long m_lastFullgcCount = 0;

    private long m_lastYounggcTime = 0;

    private long m_lastYounggcCount = 0;



    public long getM_lastGcCount() {
        return this.m_lastGcCount;
    }

    public long getM_lastGcTime() {
        return this.m_lastGcTime;
    }

    public long getM_lastFullgcTime() {
        return this.m_lastFullgcTime;
    }

    public long getM_lastFullgcCount() {
        return this.m_lastFullgcCount;
    }

    public long getM_lastYounggcTime() {
        return this.m_lastYounggcTime;
    }

    public long getM_lastYounggcCount() {
        return this.m_lastYounggcCount;
    }

    private Set<String> younggcAlgorithm = new LinkedHashSet<String>() {
        {
            add("Copy"); //copy(Serial是新生代的单线程垃圾收集器)
            add("ParNew"); //它是Serial收集器的多线程版本
            add("PS Scavenge");//新生代的收集器，可控的吞吐量
            add("G1 Young Generation");//G1垃圾回收算法 b标记清楚算法
        }
    };

    private Set<String> oldgcAlgorithm = new LinkedHashSet<String>() {
        {
            add("MarkSweepCompact"); //标记清楚算法
            add("PS MarkSweep"); //老年代 Serial Old(PS MarkSweep)收集器
            add("ConcurrentMarkSweep"); //并发标记清除算法
            add("G1 Old Generation"); //老年代的G1算法
        }
    };
    private Map<String, Number> collectGC() {
        long gcCount = 0;
        long gcTime = 0;
        long oldGCount = 0;
        long oldGcTime = 0;
        long youngGcCount = 0;
        long youngGcTime = 0;
        Map<String, Number> map = new LinkedHashMap<>();


        for (GarbageCollectorMXBean garbageCollector : ManagementFactory.getGarbageCollectorMXBeans()) {

            gcTime += garbageCollector.getCollectionTime();
            gcCount += garbageCollector.getCollectionCount();
            String gcAlgorithm = garbageCollector.getName();
            if (younggcAlgorithm.contains(gcAlgorithm)) {
                youngGcTime += garbageCollector.getCollectionTime();
                youngGcCount += garbageCollector.getCollectionCount();
            } else if (oldgcAlgorithm.contains(gcAlgorithm)) {
                oldGcTime += garbageCollector.getCollectionTime();
                oldGCount += garbageCollector.getCollectionCount();
            }
        }
        //
        //   GC实时统计信息
        //
        map.put("jvm.gc.count", gcCount - m_lastGcCount);
        map.put("jvm.gc.time", gcTime - m_lastGcTime);
        final long fullGcCount = oldGCount - m_lastFullgcCount;
        map.put("jvm.fullgc.count", fullGcCount);
        map.put("jvm.fullgc.time", oldGcTime - m_lastFullgcTime);
        map.put("jvm.younggc.count", youngGcCount - m_lastYounggcCount);
        map.put("jvm.younggc.time", youngGcTime - m_lastYounggcTime);


        if (youngGcCount > m_lastYounggcCount) {
            map.put("jvm.younggc.meantime",
                    (youngGcTime - m_lastYounggcTime) / (youngGcCount - m_lastYounggcCount));
        } else {
            map.put("jvm.younggc.meantime", 0);
        }
        //GC 增量统计信息
        m_lastGcCount = gcCount;
        m_lastGcTime = gcTime;
        m_lastYounggcCount = youngGcCount;
        m_lastYounggcTime = youngGcTime;
        m_lastFullgcCount = oldGCount;
        m_lastFullgcTime = oldGcTime;

        return map;

    }

    private static Map<String, Number> collectClassLoadingInfo() {
        ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();
        Map<String, Number> map = new LinkedHashMap<String, Number>();

        map.put("jvm.classloading.loaded.count", classLoadingMXBean.getLoadedClassCount());
        map.put("jvm.classloading.totalloaded.count", classLoadingMXBean.getTotalLoadedClassCount());
        map.put("jvm.classloading.unloaded.count", classLoadingMXBean.getUnloadedClassCount());

        return map;
    }

}
