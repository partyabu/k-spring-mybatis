package com.abucloud.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户角色关系表
 * </p>
 *
 * @author liukang
 * @since 2021-08-22
 */
@Data
public class TbUserRoleRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer userId;

    private Integer roleId;

}
