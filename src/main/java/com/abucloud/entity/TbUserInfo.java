package com.abucloud.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author liukang
 * @since 2021-08-22
 */
@Data
public class TbUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String loginAccount;

    private String password;

    private String username;

    private Integer deptId;

    private String dataStatus;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

    private Integer recordVersion;

    private Integer updateCount;
}
