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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author LZ
 * @date 2022-12-02 16:58:33
 */
@Service
@RequiredArgsConstructor
public class ActvReviewServiceImpl extends ServiceImpl<ActvReviewMapper,ActvReview> implements ActvReviewService {
    private final CmtyActvUserService cmtyActvUserService;

    @Override
    public List<ReviewVo> reviewList(HttpServletRequest request, Long userId) {
        return this.baseMapper.reviewList(request,userId);
    }

    @Override
    public void check(ReviewDto dto,HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        //查询条件
        LambdaQueryWrapper<CmtyActvUser> eq = new QueryWrapper<CmtyActvUser>().lambda().eq(CmtyActvUser::getCmtyId, dto.getCmtyId())
                .eq(CmtyActvUser::getActvId, dto.getActvId())
                .eq(CmtyActvUser::getUserId, userId);
        //1 表示同意  0 不同意
        if (1 == dto.getIsAgree()) {
            //修改该审批人审批状态为已完成
            ActvReview actvReview = this.getById(dto.getActvReviewId());
            actvReview.setStatus(2);
            this.updateById(actvReview);
            //下一个审批人
            if (actvReview.getReviewId() == 1){
                actvReview.setReviewId(2L);
            }else {
                actvReview.setReviewId(3L);
            }
            CmtyActvUser cmtyActvUser = cmtyActvUserService.getOne(eq);
            cmtyActvUser.setStatus(1);
            cmtyActvUserService.updateById(cmtyActvUser);
            //审批人状态为审核中
            actvReview.setStatus(1);
            this.save(actvReview);
        //不同意操作
        }else{

        }

    }
}
