package com.zl.lqian.readwrite.conf.aspect;

import com.zl.lqian.readwrite.db.DbContextHolder;
import com.zl.lqian.readwrite.conf.annotation.SlaveDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class MasterSlaveAspect implements Ordered {

    public static final Logger logger = LogManager.getLogger(MasterSlaveAspect.class);

    /**
     * 切换到从库切切面
     */
    @Around("@annotation(slaveDataSource)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint, SlaveDataSource slaveDataSource) throws Throwable {
        try {
            logger.debug("slaveDataSource注解开始连接切面");
            //进入切面需更改数据源
            DbContextHolder.setDbType(DbContextHolder.DbType.DBSlave);
            Object result = proceedingJoinPoint.proceed();
            return result;
        } finally {
            //必须要clear
            DbContextHolder.clearDbType();
            logger.debug("清除ThreadLocal中数据源连接");
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
