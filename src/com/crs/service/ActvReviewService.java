package com.crs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crs.dto.ReviewDto;
import com.crs.entity.ActvReview;
import com.crs.vo.ReviewVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author LZ
 * @date 2022-12-02 16:58:33
 */
public interface ActvReviewService extends IService<ActvReview> {

    List<ReviewVo> reviewList(HttpServletRequest request, Long userId);

    /**
     * 审核操作
     */
    void check(ReviewDto dto, HttpServletRequest request);

}
