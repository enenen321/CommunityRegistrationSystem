package com.crs.entity;

import com.crs.common.mybati.base.BaseEntity;
import lombok.Data;

/**
 * @author LZ
 * @date 2022-12-02 16:58:10
 */
@Data
public class SysCmty extends BaseEntity {

    /**
     * 社团名称
     */
    private String cmtyName;
    /**
     * 社团类型
     */
    private Integer cmtyType;
    /**
     * 社团团长
     */
    private Long managerId;
    /**
     * 所属学院
     */
    private Long collId;
}

