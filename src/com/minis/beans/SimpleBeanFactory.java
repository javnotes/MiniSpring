package com.minis.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luf
 * @date 2023/03/15 10:19
 **/
public class SimpleBeanFactory implements BeanFactory {
    private List<BeanDefinition> beandefinitions = new ArrayList<>();
    private List<String> beanNames = new ArrayList<>();
    private Map<String, Object> singletons = new HashMap<>();

    public SimpleBeanFactory() {
    }

    /**
     * getBean，核心方法
     */
    @Override
    public Object getBean(String beanName) throws BeansException {
        //先尝试直接获取Bean实例
        Object singleton = singletons.get(beanName);
        //如果此时还没有这个Bean的实例，则获取它的定义来创建实例
        if (singleton == null) {
            //返回指定元素第一次出现的索引，如果此列表不包含该元素，则为 -1。
            int i = beanNames.indexOf(beanName);
            if (i == -1) {
                throw new BeansException();
            } else {
                //获取Bean的定义
                BeanDefinition beanDefinition = beandefinitions.get(i);
                try {
                    singleton = Class.forName(beanDefinition.getClassName()).newInstance();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                //注册Bean实例
                singletons.put(beanDefinition.getId(), singleton);
            }
        }
        return singletons;
    }

    @Override
    public void registerBeanDefinition(BeanDefinition bd) {
        this.beandefinitions.add(bd);
        this.beanNames.add(bd.getId());
    }
}
