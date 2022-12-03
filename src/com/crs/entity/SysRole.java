package com.crs.entity;

import com.crs.common.mybati.base.BaseEntity;
import lombok.Data;

/**
 * @author LZ
 * @date 2022-12-02 16:57:04
 */
@Data
public class SysRole extends BaseEntity {

    /**
     * 角色名称
     */
    private String roleName;

}

