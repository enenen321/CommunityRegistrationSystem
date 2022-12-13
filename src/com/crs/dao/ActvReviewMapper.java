package com.crs.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crs.entity.ActvReview;
import com.crs.vo.ReviewVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author LZ
 * @date 2022-12-02 16:58:33
 */
@Mapper
public interface ActvReviewMapper extends BaseMapper<ActvReview> {

    /**
     * 审核列表
     */
    List<ReviewVo> reviewList(HttpServletRequest request,@Param("userId") Long userId);
}

