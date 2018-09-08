package com.zl.lqian.annotation;

import com.zl.lqian.config.CanalClientConfiguration;
import com.zl.lqian.config.CanalConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({CanalConfig.class, CanalClientConfiguration.class})
public @interface EnableCanalClient {


}
