package com.magic.rate_limiter.annotation;

import com.magic.rate_limiter.enums.LimitType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author mzk
 * @create 2022-07-11 13:34
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimiter {

    /**
     * 限流的 key，主要是指前缀
     * @return
     */
    String key() default "rate_limit:";

    /**
     * 限流时间窗
     * @return
     */
    int time() default 60;

    /**
     * 在时间窗内的限流次数
     * @return
     */
    int count() default 100;

    /**
     * 限流类型
     * @return
     */
    LimitType limitType() default LimitType.DEFAULT;

}
