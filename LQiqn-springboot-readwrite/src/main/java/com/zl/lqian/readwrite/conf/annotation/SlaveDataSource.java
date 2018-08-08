package com.zl.lqian.readwrite.conf.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author zl
 * 从库使用注解
 */
@Target({ TYPE, METHOD })
@Retention(RUNTIME)
public @interface SlaveDataSource {
}
