package com.magic.aware_demo;

import com.magic.aware_demo.service.BeanUtils;
import com.magic.aware_demo.service.BeanUtils2;
import com.magic.aware_demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AwareDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void test1() {
        UserService userService = BeanUtils.getBean("userService");
        userService.say();
    }

    @Test
    void test2() {
        UserService userService = BeanUtils2.getBean("userService");
        userService.say();
    }
}
