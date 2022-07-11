package com.magic.scrappy.utils;

import org.springframework.aop.framework.AopContext;

import java.util.function.Function;

/**
 * @author: xugongkai
 * @created: 2021-09-24 18:05
 */
public class AopUtils {

    @SuppressWarnings("unchecked")
    public static <T, R> R executeByProxyIns(T ins, Function<T, R> fun) {
        try {
            T proxy = (T) AopContext.currentProxy();
            return fun.apply(proxy);
        }catch (Exception ignore){
            return fun.apply(ins);
        }
    }

}
