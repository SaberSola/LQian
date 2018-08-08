package com.zl.lqian.readwrite.conf.annotation;


import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RateLimit {


    String key() default "'default'";

    String context() default "'zhanglei'";
}
