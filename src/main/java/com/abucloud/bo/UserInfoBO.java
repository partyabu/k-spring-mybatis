package com.abucloud.bo;


import com.abucloud.entity.TbUserInfo;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author party-abu
 * @Date 2022/3/23 21:59
 */
@Data
public class UserInfoBO {

    private Integer userId;

    private List<TbUserInfo> loginAccountList;
}
