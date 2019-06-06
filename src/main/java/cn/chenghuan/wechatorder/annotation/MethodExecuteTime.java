package cn.chenghuan.wechatorder.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 程欢
 * @Description 方法执行时间注解
 * @Date 2019/6/6 21:10
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodExecuteTime {
   String param() default "";
}
