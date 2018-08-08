package com.zl.lqian.readwrite.conf.aspect;


import com.zl.lqian.readwrite.conf.annotation.RateLimit;
import com.zl.lqian.readwrite.ratelimtit.RateLimiterClient;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 定义切面
 * @Author zl
 *
 */
@Component
@Aspect
public class DistributedateLimitAspect {

    private final Logger logger = LoggerFactory.getLogger(DistributedateLimitAspect.class);

    @Autowired
    private RateLimiterClient rateLimiterClient;


    @Pointcut("@annotation(com.zl.lqian.readwrite.conf.annotation.RateLimit)")
    public void rateLimt(){

    }
    @Around("rateLimt()")
    public Object rateLimt(ProceedingJoinPoint joinPoint) {
        boolean flag = true;
        Object reuslt = null;
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        RateLimit rateLimit = method.getAnnotation(RateLimit.class);
        String key = rateLimit.key();
        String context = rateLimit.context();
        try {
            //开始申请
            flag = rateLimiterClient.acquire(context,key);
            if (!flag){
                logger.debug("api 被限流 methodName :{}",method.getName());
                return reuslt;
            }
            reuslt = joinPoint.proceed();
        }catch (Throwable e){
            logger.error(" redis error methodName : {}",method.getName());
            throw new RuntimeException(e);
        }
        return reuslt;
    }
}
