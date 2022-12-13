package com.crs.dto;

import lombok.Data;

/**
 * @author LZ
 * @date 2022/12/13 15:11
 */
@Data
public class ReviewDto {

    /**
     * actv_review表id
     */
    private Long actvReviewId;
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
}
