package com.example.message.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


@Component("beanFactory")
public class BeanFactory implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public BeanFactory() {
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (BeanFactory.applicationContext == null) {
            BeanFactory.applicationContext = applicationContext;
        }

    }


    public static <T> T getBean(String beanId) {
        return (T) applicationContext.getBean(beanId);
    }


}
