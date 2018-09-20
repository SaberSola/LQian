package com.zl.lqian.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;

/**
 * 内存相关的MXBean
 */
public class MemoryInformation {


    // usedMemory 是heap使用内存 (eden+survivor+old)
    private final long m_usedMemory;

    //heap 的最大内存
    private final long m_maxMemory;

    // usedOldGen "Old Gen"使用内存(老年代)
    private final long m_usedOldGen;

    // maxOldGen "Old Gen"最大内存(老年代)
    private final long m_maxOldGen;

    // usedPermGen "Perm Gen"使用内存
    /**
     * 这里表示永久代
     * 放静态的类信息和方法信息，
     * 静态的方法和变量，
     * final标注的常量信息等。
     */
    private final long m_usedPermGen;

    //maxPermGen "Perm Gen"最大内存
    private final long m_maxPermGen;

    // usedEdenSpace "Eden Space"使用内存(年轻代)
    private final long m_usedEdenSpace;

    // maxEdenSpace "Eden Space"最大内存
    private final long m_maxEdenSpace;

    // usedSurvivorSpace "Survivor Space"使用内存(幸存者区)
    private final long m_usedSurvivorSpace;

    // maxSurvivorSpace "Survivor Space"最大内存
    private final long m_maxSurvivorSpace;

    private final long m_usedNonHeapMemory;

    private final long m_maxNonHeapMemory;

    private MBeanServer m_mbeanServer = ManagementFactory.getPlatformMBeanServer();

    private static final String DIRECT_BUFFER_MBEAN = "java.nio:type=BufferPool,name=direct";

    private static final String MAPPED_BUFFER_MBEAN = "java.nio:type=BufferPool,name=mapped";


    public MemoryInformation() {
        m_usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        m_maxMemory = Runtime.getRuntime().maxMemory();
        final MemoryPoolMXBean permGenMemoryPool = getPermGenMemoryPool();
        if (permGenMemoryPool != null) {
            final MemoryUsage usage = permGenMemoryPool.getUsage();
            //可以使用的
            m_usedPermGen = usage.getUsed();
            m_maxPermGen = usage.getMax();
        } else {
            m_usedPermGen = 0;
            m_maxPermGen = 0;
        }
        final  MemoryPoolMXBean oldGenMemoryPool = getOldGenMemoryPool();
        if (oldGenMemoryPool != null){
            final MemoryUsage usage = oldGenMemoryPool.getUsage();
            m_usedOldGen = usage.getUsed();
            m_maxOldGen = usage.getMax();
        }else {
            m_usedOldGen = 0;
            m_maxOldGen = 0;
        }
        final MemoryPoolMXBean edenSpaceMemoryPool = getEdenSpacePool();
        if (edenSpaceMemoryPool != null) {
            final MemoryUsage usage = edenSpaceMemoryPool.getUsage();
            m_usedEdenSpace = usage.getUsed();
            m_maxEdenSpace = usage.getMax();
        } else {
            m_usedEdenSpace = 0;
            m_maxEdenSpace = 0;
        }

        final MemoryPoolMXBean survivorSpacePool = getSurvivorSpaceMemoryPool();
        if (survivorSpacePool != null) {
            final MemoryUsage usage = survivorSpacePool.getUsage();
            m_usedSurvivorSpace = usage.getUsed();
            m_maxSurvivorSpace = usage.getMax();
        } else {
            m_usedSurvivorSpace = 0;
            m_maxSurvivorSpace = 0;
        }

        final MemoryUsage nonHeapMemoryUsage = ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage();
        m_usedNonHeapMemory = nonHeapMemoryUsage.getUsed();
        m_maxNonHeapMemory = nonHeapMemoryUsage.getMax();
    }

    public long getMaxEdenSpace() {
        return m_maxEdenSpace;
    }

    public long getMaxMemory() {
        return m_maxMemory;
    }

    public long getMaxNonHeapMemory() {
        return m_maxNonHeapMemory;
    }

    public long getMaxOldGen() {
        return m_maxOldGen;
    }

    public long getMaxPermGen() {
        return m_maxPermGen;
    }

    public long getMaxSurvivorSpace() {
        return m_maxSurvivorSpace;
    }

    /**
     * 获取元空间的MxBeanPool（永久代）
     *
     * @return
     */
    private MemoryPoolMXBean getPermGenMemoryPool() {
        for (final MemoryPoolMXBean memoryPool : ManagementFactory.getMemoryPoolMXBeans()) {
            if (memoryPool.getName().endsWith("Perm Gen")) {
                return memoryPool;
            }
        }
        return null;
    }

    /**
     * 获取老年代的MxbeanPool
     * @return
     */
    private MemoryPoolMXBean getOldGenMemoryPool () {
        for (final MemoryPoolMXBean memoryPool : ManagementFactory.getMemoryPoolMXBeans()) {
            if (memoryPool.getName().endsWith("Old Gen")) {
                return memoryPool;
            }
        }
        return null;
    }

    /**
     * 获取年轻代edson区
     * @return
     */
    private MemoryPoolMXBean getEdenSpacePool(){
        for (final MemoryPoolMXBean memoryPool : ManagementFactory.getMemoryPoolMXBeans()){
            if (memoryPool.getName().endsWith("Eden Space")) {
                return memoryPool;
            }
        }
        return null;
    }

    /**
     * 幸存者区
     * @return
     */
    private MemoryPoolMXBean getSurvivorSpaceMemoryPool() {
        for (final MemoryPoolMXBean memoryPool : ManagementFactory.getMemoryPoolMXBeans()) {
            if (memoryPool.getName().endsWith("Survivor Space")) {
                return memoryPool;
            }
        }
        return null;
    }

    public long getUsedDirectBufferSize() {
        long directBufferSize = 0;
        try {
            ObjectName directPool = new ObjectName(DIRECT_BUFFER_MBEAN);
            directBufferSize = (Long) m_mbeanServer.getAttribute(directPool, "MemoryUsed");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return directBufferSize;
    }

    public long getUsedEdenSpace() {
        return m_usedEdenSpace;
    }

    /**
     * edson利用率
     * @return
     */
    public double getUsedEdenSpacePercentage(){
        if (m_usedEdenSpace > 0 && m_maxEdenSpace > 0) {
            return 100d * m_usedEdenSpace / m_maxEdenSpace;
        }
        return 0d;
    }

    public long getUsedMappedSize() {
        long mappedBufferSize = 0;
        try {
            ObjectName directPool = new ObjectName(MAPPED_BUFFER_MBEAN);
            mappedBufferSize = (Long) m_mbeanServer.getAttribute(directPool, "MemoryUsed");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mappedBufferSize;
    }

    public long getUsedMemory() {
        return m_usedMemory;
    }

    public double getUsedMemoryPercentage() {
        return 100d * m_usedMemory / m_maxMemory;
    }

    public long getUsedNonHeapMemory() {
        return m_usedNonHeapMemory;
    }

    public double getUsedNonHeapPercentage() {
        if (m_usedNonHeapMemory > 0 && m_maxNonHeapMemory > 0) {
            return 100d * m_usedNonHeapMemory / m_maxNonHeapMemory;
        }
        return 0d;
    }

    public long getUsedOldGen() {
        return m_usedOldGen;
    }

    public double getUsedOldGenPercentage() {
        if (m_usedOldGen > 0 && m_maxOldGen > 0) {
            return 100d * m_usedOldGen / m_maxOldGen;
        }
        return 0d;
    }

    public long getUsedPermGen() {
        return m_usedPermGen;
    }

    public double getUsedPermGenPercentage() {
        if (m_usedPermGen > 0 && m_maxPermGen > 0) {
            return 100d * m_usedPermGen / m_maxPermGen;
        }
        return 0d;
    }

    public long getUsedSurvivorSpace() {
        return m_usedSurvivorSpace;
    }

    public double getUsedSurvivorSpacePercentage() {
        if (m_usedSurvivorSpace > 0 && m_maxSurvivorSpace > 0) {
            return 100d * m_usedSurvivorSpace / m_maxSurvivorSpace;
        }
        return 0d;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "[usedMemory=" + getUsedMemory() +
                ", maxMemory=" + getMaxMemory() + ']';
    }
}
