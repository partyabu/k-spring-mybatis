package com.abucloud.service;

import com.abucloud.bo.UserRoleBO;
import com.abucloud.bo.UserInfoBO;
import com.abucloud.entity.TbUserInfo;

import java.util.List;

/**
 * @Description:
 * @Author party-abu
 * @Date 2022/3/26 12:37
 */
public interface UserService {

    List<UserInfoBO> selectList();

    TbUserInfo selectOne(Integer id);

    List<UserRoleBO> selectRoleByCondition(TbUserInfo userInfo);

    void insertRoleUser();

    void insertOneUser();

    boolean insertUser();

}
