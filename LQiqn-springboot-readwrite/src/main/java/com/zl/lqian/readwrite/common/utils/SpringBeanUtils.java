package com.zl.lqian.readwrite.common.utils;


import org.springframework.context.ConfigurableApplicationContext;


/**
 * SpringBeanUtils.
 * @Author zl
 */
public final class SpringBeanUtils {

    private static final SpringBeanUtils INSTANCE = new SpringBeanUtils();

    private ConfigurableApplicationContext cfgContext;

    private SpringBeanUtils() {
        if (INSTANCE != null) {
            throw new Error("error");
        }
    }


    public static SpringBeanUtils getInstance() {
        return INSTANCE;
    }

    public <T> T getBean(final Class<T> type) {
        AssertUtils.notNull(type);
        return cfgContext.getBean(type);
    }

    public void registerBean(final String beanName, final Object obj) {
        AssertUtils.notNull(beanName);
        AssertUtils.notNull(obj);
        cfgContext.getBeanFactory().registerSingleton(beanName, obj);
    }

    public void setCfgContext(final ConfigurableApplicationContext cfgContext) {
        this.cfgContext = cfgContext;
    }
}
