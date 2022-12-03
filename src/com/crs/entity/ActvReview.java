package com.crs.entity;

import com.crs.common.mybati.base.BaseEntity;
import lombok.Data;

/**
 * @author LZ
 * @date 2022-12-02 16:58:33
 */
@Data
public class ActvReview extends BaseEntity {

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 活动id
     */
    private Long actvId;
    /**
     * 审核人id
     */
    private Long reviewId;
    /**
     * 审核状态
     */
    private Integer stauts;
}

