package com.zl.lqian.concurent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 启动完成后 执行此runner
 */
@Component
public class TaskApplicationRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(TaskApplicationRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
