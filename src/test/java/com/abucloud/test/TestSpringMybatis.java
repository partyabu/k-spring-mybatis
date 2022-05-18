package com.abucloud.test;

import com.abucloud.entity.TbUserInfo;
import com.abucloud.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
        // Page<TbUserInfo> pageResult = PageHelper.startPage(2, 2);
        // userService.selectList();
        // long total = pageResult.getTotal();
        // List<TbUserInfo> result = pageResult.getResult();

       //第二种，Mapper接口方式的调用，推荐这种使用方式。
        Page<TbUserInfo> pageResult02 = PageHelper.offsetPage(2, 2, false);
        userService.selectList();
        long total02 = pageResult02.getTotal();
        List<TbUserInfo> result02 = pageResult02.getResult();


        // 第三种，lambda方式分页
        // TbUserInfo tbUserInfo = new TbUserInfo();
        // tbUserInfo.setLoginAccount("400B");
        // Page<TbUserInfo> resultPage = PageHelper
        //         .startPage(2, 2)
        //         .doSelectPage(() -> userService.selectRoleByCondition(tbUserInfo));


    }

    @Test
    public void test02() {
        UserService userService = ac.getBean(UserService.class);
        userService.selectOne(1);
    }

    @Test
    public void test03() {
        UserService userService = ac.getBean(UserService.class);
        userService.insertRoleUser();
    }

    @Test
    public void test04() {
        UserService userService = ac.getBean(UserService.class);
        userService.insertRoleUser();
    }

    @Test
    public void testSaveBatch() {
        UserService userService = ac.getBean(UserService.class);

        List<TbUserInfo> userInfoList = new ArrayList<>();

        TbUserInfo tbUserInfo01 = new TbUserInfo();
        tbUserInfo01.setUserId(120);
        tbUserInfo01.setLoginAccount("1");
        tbUserInfo01.setPassword("1" + 120);
        tbUserInfo01.setUsername("1");
        tbUserInfo01.setDeptId(0);
        tbUserInfo01.setDataStatus("1");
        tbUserInfo01.setCreateBy("1");
        tbUserInfo01.setCreateTime(LocalDateTime.now());
        tbUserInfo01.setUpdateBy("1");
        tbUserInfo01.setUpdateTime(LocalDateTime.now());
        tbUserInfo01.setRecordVersion(0);
        tbUserInfo01.setUpdateCount(0);
        userInfoList.add(tbUserInfo01);


        TbUserInfo tbUserInfo02 = new TbUserInfo();
        tbUserInfo02.setUserId(121);
        tbUserInfo02.setLoginAccount("1");
        tbUserInfo02.setPassword("1" + 12212);
        tbUserInfo02.setUsername("1");
        tbUserInfo02.setDeptId(0);
        tbUserInfo02.setDataStatus("1");
        tbUserInfo02.setCreateBy("1");
        tbUserInfo02.setCreateTime(LocalDateTime.now());
        tbUserInfo02.setUpdateBy("1");
        tbUserInfo02.setUpdateTime(LocalDateTime.now());
        tbUserInfo02.setRecordVersion(0);
        tbUserInfo02.setUpdateCount(0);
        userInfoList.add(tbUserInfo02);

        userService.insertUser(userInfoList, 1);
    }

    @Test
    public void testDeleteBatch() {
        UserService userService = ac.getBean(UserService.class);
        userService.deleteUsers(Arrays.asList(157, 158, 159), 10);
    }

    @Test
    public void test05() {
        UserService userService = ac.getBean(UserService.class);
        TbUserInfo tbUserInfo = new TbUserInfo();
        userService.updateUserByConditions(tbUserInfo);
    }
}
