package com.magic.aware_demo.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * @author mzk
 * @create 2022-07-11 10:28
 */
@Component
public class BeanUtils implements BeanFactoryAware {
    public static BeanFactory beanFactory;


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        BeanUtils.beanFactory = beanFactory;
    }

    public static <T> T getBean(String beanName){
        return (T)beanFactory.getBean(beanName);
    }
}
