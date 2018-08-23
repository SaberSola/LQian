package com.lqian.repetition.aspect;


import com.alibaba.fastjson.JSONObject;
import com.lqian.repetition.lock.DistributedLock;
import org.apache.ibatis.javassist.ClassClassPath;
import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtMethod;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class ConcurrentLimitAspect {


    private final String KEY_PREFIX = "zhangleiconcurrent";

    private final Logger logger = LoggerFactory.getLogger(ConcurrentLimitAspect.class);

    @Autowired
    private DistributedLock distributedLock;


    @Pointcut("@annotation(com.lqian.repetition.conf.annotation.ConcurrentLimit)")
    private void concurrentLimit() {

    }


    @Around("concurrentLimit()")
    public Object concurrentLimit(ProceedingJoinPoint point) throws Throwable {
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] strings = methodSignature.getParameterNames();
        String targetClass = point.getTarget().getClass().getName();
        String targetMethod = signature.getName();
        String queryString = Arrays.toString(strings);
        String qeeryvalue = Arrays.toString(point.getArgs());
        String result = queryString+ qeeryvalue;
       // String repeatDataValidator = repeatDataValidator(request);
        //其实可以考虑前段传递过来一串随机数
        String redisConcurrent = KEY_PREFIX + ":" + targetClass + ":" + targetMethod + ":" + result;
        //这里开始上锁
        boolean lock = distributedLock.lock(redisConcurrent);
        if (!lock) {
            System.out.println("重复提交 : " + redisConcurrent);
            return null;
        }
        //得到锁,执行方法，释放锁
        try {
            return point.proceed();
        } catch (Exception e) {
            logger.error("process ,method : {} 出错", targetMethod, e);
            throw e;
        } finally {
            boolean releaseResult = distributedLock.releaseLock(redisConcurrent);
            logger.debug("release lock : " + redisConcurrent + (releaseResult ? " success" : " failed"));
        }
    }


    public String repeatDataValidator(HttpServletRequest httpServletRequest) {
        String params = JSONObject.toJSONString(httpServletRequest.getParameterMap());
        String url = httpServletRequest.getRequestURI();
        Map<String, String> map = new HashMap<String, String>();
        map.put(url, params);
        String nowUrlParams = map.toString();//
        return nowUrlParams;
    }
}
