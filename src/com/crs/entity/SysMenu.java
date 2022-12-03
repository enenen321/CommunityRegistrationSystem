package com.crs.entity;

import com.crs.common.mybati.base.BaseEntity;
import lombok.Data;
/**
 * @author LZ
 * @date 2022-12-02 16:57:22
 */
@Data
public class SysMenu extends BaseEntity {

    /**
     * 父id
     */
    private Long parentId;
    /**
     * 菜单名
     */
    private String menuName;
    /**
     * 权限路径
     */
    private String permission;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 排序
     */
    private Integer sort;
}

