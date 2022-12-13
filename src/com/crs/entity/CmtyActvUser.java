package com.crs.entity;

import com.crs.common.mybati.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author LZ
 * @date 2022-12-02 16:58:23
 */
@Data
@Accessors(chain = true)
public class CmtyActvUser extends BaseEntity {

    /**
     * 社团id
     */
    private Long cmtyId;
    /**
     * 活动id
     */
    private Long actvId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 审核状态 0-未审核；1-已审核
     */
    private Integer status;
    /**
     * 不同意原因
     */
    private String reason;
    /**
     * 当前审核人
     */
    private Long reviewId;
}

