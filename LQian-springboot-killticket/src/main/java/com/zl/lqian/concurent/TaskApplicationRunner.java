package com.zl.lqian.concurent;

import com.zl.lqian.configuration.TicketConfig;
import com.zl.lqian.service.MonterTicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 启动完成后 执行此runner
 */
@Component
public class TaskApplicationRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(TaskApplicationRunner.class);

    private MonterTicketService monterTicketService;

    private TicketConfig ticketConfig;
    @Autowired
    public TaskApplicationRunner(final MonterTicketService service ,TicketConfig ticketConfig){
        this.monterTicketService = service;
        this.ticketConfig = ticketConfig;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
