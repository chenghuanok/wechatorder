package cn.chenghuan.wechatorder.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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
        //执行方法
        joinPoint.proceed();
        // 方法执行时间
        final long executeTime1 = System.currentTimeMillis() - startTime;
        final Object[] args = joinPoint.getArgs();
        if(logger.isInfoEnabled()){
            logger.info("方法"+ args[0]+"执行时间为："+executeTime1);
        }
    }
}
