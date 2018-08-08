package com.zl.lqian.readwrite.conf.aspect;


import com.zl.lqian.readwrite.conf.annotation.Locked;
import com.zl.lqian.readwrite.lock.DistributedLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class DistributedLockAspect {
    private final Logger logger = LoggerFactory.getLogger(DistributedLockAspect.class);

    @Autowired
    private DistributedLock distributedLock;

    private ExpressionParser parser = new SpelExpressionParser();

    private LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

    @Pointcut("@annotation(com.zl.lqian.readwrite.conf.annotation.Locked)")
    private void lockPoint(){

    }

    @Around("lockPoint()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        //获取注解的方法
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        //获取注解=
        Locked locked = method.getAnnotation(Locked.class);
        String key = locked.value();
        Object[] args = pjp.getArgs();
        key = parse(key, method, args);
        //重试次数
        int retryTimes = locked.action().equals(Locked.LockFailAction.CONTINUE) ? locked.retryTimes() : 0;
        //开始加锁
        boolean lock = distributedLock.lock(key, locked.keepMills(), retryTimes, locked.sleepMills());
        if(!lock) {
            logger.debug("get lock failed : " + key);
            return null;
        }

        //得到锁,执行方法，释放锁
        logger.debug("get lock success : " + key);
        try {
            return  pjp.proceed();
        }catch (Exception e){
            logger.error("process ,method : {} 出错",method.getName(),e);
             throw  e;
        } finally {
            boolean releaseResult = distributedLock.releaseLock(key);
            logger.debug("release lock : " + key + (releaseResult ? " success" : " failed"));
        }
    }

    /**
     *
     * 对spring el表达式的支持
     * @param key
     * @param method
     * @param args
     * @return
     */
    private String parse(String key, Method method, Object[] args) {
        String[] params = discoverer.getParameterNames(method);
        EvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < params.length; i ++) {
            context.setVariable(params[i], args[i]);
        }
        return parser.parseExpression(key).getValue(context, String.class);
    }

}
