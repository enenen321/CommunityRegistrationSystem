package com.crs.entity;

import com.crs.common.mybati.base.BaseEntity;
import lombok.Data;

/**
 * @author LZ
 * @date 2022-12-02 16:57:46
 */
@Data
public class SysRoleMenu extends BaseEntity {

    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 菜单id
     */
    private Long menuId;
}

