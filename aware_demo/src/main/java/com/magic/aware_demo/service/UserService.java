package com.magic.aware_demo.service;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;

/**
 * @author mzk
 * @create 2022-07-11 10:27
 */
@Service
public class UserService implements BeanNameAware {

    public void say(){
        System.out.println("say hello world");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("bean name is " + name);
    }
}
