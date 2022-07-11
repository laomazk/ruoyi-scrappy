package com.magic.aware_demo.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author mzk
 * @create 2022-07-11 10:44
 */
@Component
public class BeanUtils2 implements ApplicationContextAware {
    public static ApplicationContext applicationContext;



    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static <T> T getBean(String beanName) {
        return (T)applicationContext.getBean(beanName);
    }



}
