package com.abucloud.service.impl;

import com.abucloud.bo.UserInfoBO;
import com.abucloud.bo.UserRoleBO;
import com.abucloud.entity.TbUserInfo;
import com.abucloud.mapper.UserInfoMapper;
import com.abucloud.service.UserService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description:
 * @Author party-abu
 * @Date 2022/3/26 12:37
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

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

    @Override
    public boolean insertUser() {

        // 指定Session为BATCH状态，不自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);

        for (int i = 1; i <= 10; i++) {
            TbUserInfo tbUserInfo = new TbUserInfo();
            tbUserInfo.setUserId(i);
            tbUserInfo.setLoginAccount("1");
            tbUserInfo.setPassword("1" + i);
            tbUserInfo.setUsername("1");
            tbUserInfo.setDeptId(0);
            tbUserInfo.setDataStatus("1");
            tbUserInfo.setCreateBy("1");
            tbUserInfo.setCreateTime(LocalDateTime.now());
            tbUserInfo.setUpdateBy("1");
            tbUserInfo.setUpdateTime(LocalDateTime.now());
            tbUserInfo.setRecordVersion(0);
            tbUserInfo.setUpdateCount(0);
            userInfoMapper.insertUser(tbUserInfo);
        }
        sqlSession.close();
        sqlSession.clearCache();

        return true;
    }
}
