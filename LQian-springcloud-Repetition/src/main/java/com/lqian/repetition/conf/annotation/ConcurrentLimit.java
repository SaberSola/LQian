package com.lqian.repetition.conf.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConcurrentLimit {



    String desc() default "防止接口重复提交";

}
