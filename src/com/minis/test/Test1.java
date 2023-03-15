package com.minis.test;

import com.minis.context.ClassPathXmlApplicationContext;
import com.minis.beans.BeansException;

/**
 * @author luf
 * @date 2023/03/15 08:36
 **/
public class Test1 {
    public static void main(String[] args) throws BeansException {
        ClassPathXmlApplicationContext ctx = new
                ClassPathXmlApplicationContext("beans.xml");
        AService aService = (AService) ctx.getBean("aservice");
        aService.sayHello();
    }
}