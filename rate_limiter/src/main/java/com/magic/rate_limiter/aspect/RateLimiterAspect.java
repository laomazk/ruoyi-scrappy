package com.magic.rate_limiter.aspect;

import com.magic.rate_limiter.annotation.RateLimiter;
import com.magic.rate_limiter.enums.LimitType;
import com.magic.rate_limiter.exception.RateLimitException;
import com.magic.rate_limiter.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Collections;

/**
 * @author mzk
 * @create 2022-07-11 13:40
 */
@Component
@Aspect
@Slf4j
public class RateLimiterAspect {
    @Autowired
    private DefaultRedisScript<Long> redisScript;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Before("@annotation(rateLimiter)")
    public void before(JoinPoint jp, RateLimiter rateLimiter) throws RateLimitException {
        int time = rateLimiter.time();
        int count = rateLimiter.count();
        String combineKey = getCombineKey(rateLimiter, jp);
        try {
            Long number = redisTemplate.execute(redisScript, Collections.singletonList(combineKey), time, count);
            if(number == null || number.intValue() > count){
                //超过限流阈值
                log.info("当前接口以达到最大限流次数");
                throw new RateLimitException("访问过于频繁，请稍后访问");
            }
            log.info("一个时间窗内请求次数：{}，当前请求次数：{}，缓存的 key 为 {}", count, number, combineKey);
        } catch (Exception e) {
            throw e;
        }

    }

    private String getCombineKey(RateLimiter rateLimiter, JoinPoint jp) {
        StringBuilder key = new StringBuilder(rateLimiter.key());
        if (LimitType.IP == rateLimiter.limitType()) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            key.append(IpUtils.getIpAddr(attributes.getRequest())).append("-");
        }
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        key.append(method.getDeclaringClass().getName())
                .append("-")
                .append(method.getName());
        return key.toString();
    }

}
