package com.zl.lqian.ConditionBean;

import com.sun.jdmk.comm.HtmlAdaptorServer;
import com.zl.lqian.StandardMBean.domain.Hello;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class HelloAgent {


    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException,
            InstanceAlreadyExistsException, MBeanRegistrationException {

        MBeanServer server  =  ManagementFactory.getPlatformMBeanServer();
        ObjectName helloName = new ObjectName("MyMBean:name=HelloWorld");
        Hello hello = new Hello();
        server.registerMBean(hello,helloName);
        ObjectName adapterName = new ObjectName("MyBean:name=htmladapter,port=8082");
        HtmlAdaptorServer adapter = new HtmlAdaptorServer();
        server.registerMBean(adapter,adapterName);
        Zl zl = new Zl();
        server.registerMBean(zl,new ObjectName("MyMBean:name=zl"));
        //注册监听器 监听器 过滤 回调类
        zl.addNotificationListener(new HelloListener(),null,hello);
        adapter.start();
    }
}
