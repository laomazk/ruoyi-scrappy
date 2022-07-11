package com.magic.spel_params.aspect;

import com.magic.spel_params.annotation.EnableNodeMetricsReport;
import com.magic.spel_params.utils.SpelUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author mzk
 * @create 2022-07-09 10:25
 */
@Aspect
@Component
@Slf4j
public class NodeMetricsAspect {

    @Around("@annotation(nodeMetrics)")
    public Object process(ProceedingJoinPoint pjp, EnableNodeMetricsReport nodeMetrics) throws Throwable {
        String objIdRegx = nodeMetrics.objId();
        log.info("objIdRegx：{}", objIdRegx);
        String objId = SpelUtils.generateKeyBySpEL(objIdRegx, pjp);
        log.info("objId: {}", objId);
        // todo 处理目标方法
        return pjp.proceed();
    }
}
