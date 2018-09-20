package com.zl.lqian.client;

import com.zl.lqian.StandardMBean.domain.HelloMBean;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class Client {

    public static void main(String[] args) throws IOException,
            MalformedObjectNameException, InstanceNotFoundException,
            AttributeNotFoundException, InvalidAttributeValueException,
            MBeanException, ReflectionException, IntrospectionException {

        String domainName = "MyMBean";
        int rmiPort = 1099;
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:"+rmiPort+"/"+domainName);

        //建立连接
        JMXConnector jmxc  = JMXConnectorFactory.connect(url);
        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

        //print domains
        System.out.println("Domains:------------------");

        //遍历每个mbean
        String[] domains = mbsc.getDomains();
        for (int i = 0; i < domains.length; i ++){
            System.out.println("\tDomain["+i+"] = "+domains[i]);
        }
        //Mbean 的count
        System.out.println("MBean count = "+mbsc.getMBeanCount());
        //process attribute
        ObjectName mBeanName = new ObjectName(domainName+":name=HelloWorld");
        mbsc.setAttribute(mBeanName, new Attribute("Name","zl"));//注意这里是Name而不是name
        System.out.println("Name = "+mbsc.getAttribute(mBeanName, "Name"));
        //接下去是执行Hello中的printHello方法，分别通过代理和rmi的方式执行
        //动态代理的方法
        HelloMBean proxy = MBeanServerInvocationHandler.newProxyInstance(mbsc,mBeanName, HelloMBean.class, false);
        proxy.printHello();
        proxy.printHello("jizhi boy");

        //via rmi
        mbsc.invoke(mBeanName, "printHello", null, null);
        mbsc.invoke(mBeanName, "printHello", new String[]{"jizhi gril"}, new String[]{String.class.getName()});

        //获取mbean的information
        MBeanInfo info = mbsc.getMBeanInfo(mBeanName);
        System.out.println("Hello Class: "+info.getClassName());

        //获取参数
        for(int i=0;i<info.getAttributes().length;i++){
            System.out.println("Hello Attribute:"+info.getAttributes()[i].getName());
        }
        //获取方法名称
        for(int i=0;i<info.getOperations().length;i++){
            System.out.println("Hello Operation:"+info.getOperations()[i].getName());
        }

        //ObjectName of MBean
        System.out.println("all ObjectName:--------------");

        Set<ObjectInstance> set = mbsc.queryMBeans(null, null);

        for(Iterator<ObjectInstance> it = set.iterator(); it.hasNext();){
            ObjectInstance oi = it.next();
            System.out.println("\t"+oi.getObjectName());
        }
        jmxc.close();
    }
}
