package com.crs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crs.dao.ActvReviewMapper;
import com.crs.dto.ReviewDto;
import com.crs.entity.ActvReview;
import com.crs.entity.CmtyActvUser;
import com.crs.service.ActvReviewService;
import com.crs.service.CmtyActvUserService;
import com.crs.vo.ReviewVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author LZ
 * @date 2022-12-02 16:58:33
 */
@Service
public class ActvReviewServiceImpl extends ServiceImpl<ActvReviewMapper,ActvReview> implements ActvReviewService {

    @Autowired
    private CmtyActvUserService cmtyActvUserService;

    @Override
    public List<ReviewVo> reviewList(HttpServletRequest request, Long userId) {
        return this.baseMapper.reviewList(request,userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void check(ReviewDto dto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        //查询条件
        LambdaQueryWrapper<ActvReview> eq = new QueryWrapper<ActvReview>().lambda().eq(ActvReview::getReviewId, userId).eq(ActvReview::getActvId, dto.getActvId())
                .eq(ActvReview::getUserId, dto.getApplyUserId());
        //1 表示同意  0 不同意
        if (1 == dto.getIsAgree()) {
            CmtyActvUser cmtyActvUser = cmtyActvUserService.getById(dto.getCmtyActvUserId());
            //修改该审批人审批状态为已完成
            ActvReview actvReview = this.getOne(eq);
            actvReview.setStatus(2);
            cmtyActvUser.setStatus(1);
            this.updateById(actvReview);
            actvReview.setStatus(1);
            actvReview.setId(null);
            //下一个审批人
            if (userId == 1){
                actvReview.setReviewId(2L);
                cmtyActvUser.setReviewId(2L);
            }else if (userId == 2){
                actvReview.setReviewId(3L);
                cmtyActvUser.setReviewId(3L);
            }else{
                actvReview.setStatus(2);
                cmtyActvUser.setStatus(2);
                cmtyActvUser.setReviewId(0L);
            }
            cmtyActvUserService.updateById(cmtyActvUser);
            //审批人状态为审核中
            this.save(actvReview);
        //不同意操作
        }else{
            ActvReview actvReview = this.getOne(eq);
            actvReview.setStatus(2);
            actvReview.setReason(dto.getReason());
            this.updateById(actvReview);
            CmtyActvUser cmtyActvUser = cmtyActvUserService.getById(dto.getCmtyActvUserId());
            cmtyActvUser.setStatus(3);
            cmtyActvUser.setReason(dto.getReason());
            cmtyActvUserService.updateById(cmtyActvUser);
        }

    }
}
