package com.zl.lqian.util;

import org.springframework.context.ConfigurableApplicationContext;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class SpringBeanUtils {


    private static final SpringBeanUtils INSTANCE = new SpringBeanUtils();

    private ConfigurableApplicationContext cfgContext;

    private SpringBeanUtils() {
        if (INSTANCE != null) {
            throw new Error("error");
        }
    }

    /**
     * get SpringBeanUtils.
     * @return SpringBeanUtils
     */
    public static SpringBeanUtils getInstance() {
        return INSTANCE;
    }

    /**
     * acquire spring bean.
     *
     * @param type type
     * @param <T>  class
     * @return bean
     */
    public <T> T getBean(final Class<T> type) {
        return cfgContext.getBean(type);
    }

    /**
     * register bean in spring ioc.
     * @param beanName bean name
     * @param obj bean
     */
    public void registerBean(final String beanName, final Object obj) {
        cfgContext.getBeanFactory().registerSingleton(beanName, obj);
    }

    /**
     * set application context.
     * @param cfgContext application context
     */
    public void setCfgContext(final ConfigurableApplicationContext cfgContext) {
        this.cfgContext = cfgContext;
    }


    /**
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> getBeansOfType(Class<T> clazz) {
        //声明一个结果
        Map<String, T> map;
        try {
            //获取类型
            map = cfgContext.getBeansOfType(clazz);
        } catch (Exception e) {
            map = null;
        }
        //返回 bean 的类型
        return map == null ? null : new ArrayList<>(map.values());
    }

    /**
     * 获取某注解所有类型的bean
     * @param anno
     * @return
     */
    public  Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> anno) {
        Map<String, Object> map;
        try {
            //获取注解的 bean
            map = cfgContext.getBeansWithAnnotation(anno);
        } catch (Exception e) {
            map = null;
        }
        return map;
    }

    public static void main(String[] args){
        Map map = new HashMap();
        for (int i= 0; i< 50000; i ++){
            String s = String.valueOf(i);
            map.put(i,s);
        }
        System.out.println(map.size());

    }
}
