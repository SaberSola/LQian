package com.zl.lqian.annotation.content;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.zl.lqian.annotation.ListenPoint;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 删除操作监听器
 *
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ListenPoint(eventType = CanalEntry.EventType.DELETE)
public @interface DeleteListenPoint {

    /**
     * canal 指令
     * default for all
     */
    @AliasFor(annotation = ListenPoint.class)
    String destination() default "";


    /**
     * 数据库实例
     */
    @AliasFor(annotation = ListenPoint.class)
    String[] schema() default {};

    /**
     * 监听的表
     * default for al
     */
    @AliasFor(annotation = ListenPoint.class)
    String[] table() default {};

}
