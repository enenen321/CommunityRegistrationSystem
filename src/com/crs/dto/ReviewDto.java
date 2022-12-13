package com.crs.dto;

import lombok.Data;

/**
 * @author LZ
 * @date 2022/12/13 15:11
 */
@Data
public class ReviewDto {

    /**
     * cmty_actv_user表id
     */
    private Long cmtyActvUserId;
    /**
     * 是否同意 1 同意 0 不同意
     */
    private Integer isAgree;
    /**
     * 不同意的原因
     */
    private String reason;
    /**
     * 社团id
     */
    private Long cmtyId;
    /**
     * 活动id
     */
    private Long actvId;
    /**
     * 申请用户id
     */
    private Long applyUserId;
}
