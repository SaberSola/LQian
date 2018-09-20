package com.zl.lqian.ManagementFactory;


import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.LinkedHashMap;
import java.util.Map;

/*****
 *
 * ManagementFactory是一个为我们提供各种获取JVM信息的工厂类，
 * 使用ManagementFactory可以获取大量的运行时JVM信息，
 * 比如JVM堆的使用情况，以及GC情况，线程信息等，通过这些数据项我们可以了解正在运行的JVM的情况，以便我们可以做出相应的调整。
 */
public class ManagementDemo {


    public static void  main(String[] args) throws Exception{
        ManagementDemo managementDemo = new ManagementDemo();
        Map<String, Number>  map= managementDemo.collectThreadInfo();
        for (Map.Entry<String,Number> entry: map.entrySet()){
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }

    /**
     * 下面的代码可以获取当前JVM内的线程数量，
     * 并且可以计算出每种状态下的线程数量
     * @return
     */
    private static Map<String, Number> collectThreadInfo() {

        final ThreadMXBean threadBean  = ManagementFactory.getThreadMXBean();
        Map<String, Number> map = new LinkedHashMap<>();
        map.put("jvm.thread.count", threadBean.getThreadCount()); //线程数量
        map.put("jvm.thread.daemon.count", threadBean.getDaemonThreadCount());//守护线程的数量
        map.put("jvm.thread.totalstarted.count", threadBean.getTotalStartedThreadCount());// start 状态的线程数量
        ThreadInfo[] threadInfos = threadBean.getThreadInfo(threadBean.getAllThreadIds());//包含所有线程信息的数组

        int newThreadCount = 0;
        int runnableThreadCount = 0;
        int blockedThreadCount = 0;
        int waitThreadCount = 0;
        int timeWaitThreadCount = 0;
        int terminatedThreadCount = 0;

        if (threadInfos != null){
            for (ThreadInfo threadInfo : threadInfos){
                if (threadInfo != null){
                    //线程的状态
                    switch (threadInfo.getThreadState()) {
                        case NEW:
                            newThreadCount++;
                            break;
                        case RUNNABLE:
                            runnableThreadCount++;
                            break;
                        case BLOCKED:
                            blockedThreadCount++;
                            break;
                        case WAITING:
                            waitThreadCount++;
                            break;
                        case TIMED_WAITING:
                            timeWaitThreadCount++;
                            break;
                        case TERMINATED:
                            terminatedThreadCount++;
                            break;
                        default:
                            break;
                    }
                }else {
                    /*
                     * If a thread of a given ID is not alive or does not exist,
                     * the corresponding element in the returned array will,
                     * contain null,because is mut exist ,so the thread is terminated
                     * 这是代表线程结束处于中止的状态
                     */
                    terminatedThreadCount++;
                }
            }
        }
        map.put("jvm.thread.new.count", newThreadCount);
        map.put("jvm.thread.runnable.count", runnableThreadCount);
        map.put("jvm.thread.blocked.count", blockedThreadCount);
        map.put("jvm.thread.waiting.count", waitThreadCount);
        map.put("jvm.thread.time_waiting.count", timeWaitThreadCount);
        map.put("jvm.thread.terminated.count", terminatedThreadCount);

        //这里找到处于死锁的线程
        long[] ids = threadBean.findDeadlockedThreads();
        map.put("jvm.thread.deadlock.count", ids == null ? 0 : ids.length);
        return map;
    }



}
