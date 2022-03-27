package com.abucloud.test;

import com.abucloud.entity.TbUserInfo;
import com.abucloud.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Description:
 * @Author party-abu
 * @Date 2022/3/26 12:02
 */
public class TestSpringMybatis {

    private ApplicationContext ac;

    @Before
    public void beforeExecute() {
        // 获取ioc容器
        ac = new ClassPathXmlApplicationContext("applicationContext.xml");

    }

    @Test
    public void test01() {

        // 获取ioc容器
        ac = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 获取bean实例
        UserService userService = ac.getBean(UserService.class);
        // 执行方法

        // 第一种，Mapper接口方式的调用，推荐这种使用方式。
        Page<Object> pageResult = PageHelper.startPage(2, 2);
        userService.selectList();
        long total = pageResult.getTotal();
        List<Object> result = pageResult.getResult();

        //第二种，Mapper接口方式的调用，推荐这种使用方式。
        Page<Object> pageResult02 = PageHelper.offsetPage(2, 2, false);
        userService.selectList();
        long total02 = pageResult02.getTotal();
        List<Object> result02 = pageResult02.getResult();


        // 第三种，lambda方式分页
        TbUserInfo tbUserInfo = new TbUserInfo();
        tbUserInfo.setLoginAccount("400B");
        Page<Object> objects = PageHelper
                .startPage(2, 2)
                .doSelectPage(() -> userService.selectRoleByCondition(tbUserInfo));
    }

    @Test
    public void test02() {
        UserService userService = ac.getBean(UserService.class);
        userService.selectOne(1);
    }
}
