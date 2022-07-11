package com.magic.rate_limiter.enums;

/**
 * @author mzk
 * @create 2022-07-11 13:35
 */
public enum LimitType {

    /**
     * 默认的限流策略，针对某一个接口进行限流
     */
    DEFAULT,
    /**
     * 针对某一个 IP 进行限流
     */
    IP
}
