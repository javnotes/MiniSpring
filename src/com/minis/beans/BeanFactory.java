package com.minis.beans;

import com.minis.BeanDefinition;

/**
 * 功能一：获取一个 Bean（getBean），
 * 功能二：注册一个 BeanDefinition
 */
public interface BeanFactory {
    Object getBean(String beanName) throws BeansException;

    void registerBeanDefinition(BeanDefinition bd);
}
