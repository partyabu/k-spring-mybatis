package com.abucloud.service.impl;

import com.abucloud.bo.UserInfoBO;
import com.abucloud.bo.UserRoleBO;
import com.abucloud.entity.TbUserInfo;
import com.abucloud.mapper.UserInfoMapper;
import com.abucloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author party-abu
 * @Date 2022/3/26 12:37
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfoBO> selectList() {
        return this.userInfoMapper.selectList();
    }

    @Override
    public TbUserInfo selectOne(Integer id) {
        return this.userInfoMapper.selectOne(id);
    }

    @Override
    public List<UserRoleBO> selectRoleByCondition(TbUserInfo userInfo) {
        return this.userInfoMapper.selectRoleByCondition(userInfo);
    }
}
