package com.magic.rate_limiter.controller;

import com.magic.rate_limiter.annotation.RateLimiter;
import com.magic.rate_limiter.enums.LimitType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mzk
 * @create 2022-07-11 11:31
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    /**
     * 限流，10 秒之内，这个接口可以访问 3 次
     */
    @RateLimiter(time = 10, count = 3,limitType = LimitType.IP)
    public String hello() {
        return "hello";
    }
}
