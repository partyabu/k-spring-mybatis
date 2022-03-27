package com.abucloud.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 角色信息表
 * </p>
 *
 * @author liukang
 * @since 2021-08-22
 */
@Data
public class TbRoleInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer roleId;

    private String roleName;

    private String roleDesc;

    private String dataStatus;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

    private Integer recordVersion;

    private Integer updateCount;
}
