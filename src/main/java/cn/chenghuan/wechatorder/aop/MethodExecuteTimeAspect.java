package cn.chenghuan.wechatorder.aop;

import cn.chenghuan.wechatorder.annotation.MethodExecuteTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author 程欢
 * @Description 方法执行时间切面
 * @Date 2019/6/6 21:16
 */
@Aspect
@Component
public class MethodExecuteTimeAspect {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(MethodExecuteTimeAspect.class);

    @Pointcut("@annotation(cn.chenghuan.wechatorder.annotation.MethodExecuteTime)")
    public void executeTime(){}

    @Around("executeTime()")
    public void  around(final ProceedingJoinPoint joinPoint)throws  Throwable{
        System.out.println("进入。。。。。。。。aop");
        //获取方法进入前时间
        final long startTime = System.currentTimeMillis();
        final MethodSignature methodSignature  =(MethodSignature)joinPoint.getSignature();
        //获取注解
        final MethodExecuteTime methodExecuteTime = methodSignature.getClass().getAnnotation(MethodExecuteTime.class);
        //执行方法
        joinPoint.proceed();
        // 方法执行时间
        final long executeTime1 = System.currentTimeMillis() - startTime;
        //获取参数值
        //final Object[] args = joinPoint.getArgs();
        if(logger.isInfoEnabled()){
            logger.info("方法"+ methodSignature.getMethod()+"执行时间为："+executeTime1);
        }
    }
}
