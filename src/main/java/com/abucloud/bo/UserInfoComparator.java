package com.abucloud.bo;

import com.abucloud.entity.TbUserInfo;

import java.util.Comparator;

/**
 * @Description:
 * @Author party-abu
 * @Date 2022/3/31 18:37
 */
public class UserInfoComparator implements Comparator<TbUserInfo> {

    @Override
    public int compare(TbUserInfo o1, TbUserInfo o2) {
        int nano01 = o1.getCreateTime().getNano();
        int nano02 = o2.getCreateTime().getNano();
        int returnNum;
        if (nano01 == nano02) {
            returnNum = o2.getLoginAccount().compareTo(o1.getLoginAccount());
        } else {
            returnNum = nano01 - nano02;
        }
        return returnNum;
    }
}
