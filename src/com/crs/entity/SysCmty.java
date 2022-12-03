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
}

