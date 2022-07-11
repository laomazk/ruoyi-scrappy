package com.magic.spel_params.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author mzk
 * @create 2022-07-09 10:26
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableNodeMetricsReport {

    /**
     * 事务类型
     */
    String transactionType() default "";

    /**
     * 当前节点ID
     */
    long nodeId() default 0;

    /**
     * 业务主键
     * 格式:
     * 1.请求参数为param，#参数名称 -- 例:#objId
     * 2.请求参数为body，#实体名称.字段名称 -- 例:#nodeMetricsDto.objId
     */
    String objId() default "#objId";

    /**
     * 业务对象类型
     */
    String objType() default "";

    /**
     * 节点事件: 通常情况下，流程节点只会产生一类事件，所以默认eventType=DEFAULT即可
     */
    String eventType() default "_default";

    String identityMappingType() default "_default";
}
