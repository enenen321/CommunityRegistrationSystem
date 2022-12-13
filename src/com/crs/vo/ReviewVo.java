package com.crs.vo;

import com.crs.entity.ActvReview;
import lombok.Data;

/**
 * @author LZ
 * @date 2022/12/13 9:30
 */
@Data
public class ReviewVo extends ActvReview {

    /**
     * 用户名
     */
    private String username;
    /**
     * 活动主题
     */
    private String actvTitle;
}
