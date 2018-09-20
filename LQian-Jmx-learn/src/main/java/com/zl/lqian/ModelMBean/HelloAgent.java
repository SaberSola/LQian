package com.zl.lqian.ModelMBean;

import com.sun.jdmk.comm.HtmlAdaptorServer;

import javax.management.*;
import javax.management.modelmbean.RequiredModelMBean;
import java.lang.management.ManagementFactory;

public class HelloAgent {

    public static void main(String[] args) throws MalformedObjectNameException,
            NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();

        ObjectName helloName = new ObjectName("MyMBean:name=HelloWorld");
        //注意这里不是new hello();
        RequiredModelMBean hello = ModelMBeanUtils.createModelerMBean();
        server.registerMBean(hello, helloName);

        ObjectName adapterName = new ObjectName("MyMBean:name=htmladapter,port=8082");
        HtmlAdaptorServer adapter = new HtmlAdaptorServer();
        server.registerMBean(adapter, adapterName);
        adapter.start();
    }
}
