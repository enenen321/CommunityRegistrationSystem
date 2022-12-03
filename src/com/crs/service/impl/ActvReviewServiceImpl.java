package com.crs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crs.entity.ActvReview;
import com.crs.dao.ActvReviewMapper;
import com.crs.service.ActvReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author LZ
 * @date 2022-12-02 16:58:33
 */
@Service
@RequiredArgsConstructor
public class ActvReviewServiceImpl extends ServiceImpl<ActvReviewMapper,ActvReview> implements ActvReviewService {

}
