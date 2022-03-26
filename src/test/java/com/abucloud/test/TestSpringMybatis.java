package com.abucloud.test;

import com.abucloud.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:
 * @Author party-abu
 * @Date 2022/3/26 12:02
 */
public class TestSpringMybatis {

    @Test
    public void test01() {

        // 获取ioc容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 获取bean实例
        UserService userService = ac.getBean(UserService.class);
        // 执行方法
        userService.selectOne(1);
    }

}
