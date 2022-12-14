package com.crs.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crs.dao.ActvReviewMapper;
import com.crs.dto.ReviewDto;
import com.crs.entity.Actv;
import com.crs.entity.ActvReview;
import com.crs.entity.CmtyActvUser;
import com.crs.entity.SysUser;
import com.crs.service.ActvReviewService;
import com.crs.service.ActvService;
import com.crs.service.CmtyActvUserService;
import com.crs.service.SysUserService;
import com.crs.vo.ReviewVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LZ
 * @date 2022-12-02 16:58:33
 */
@Service
public class ActvReviewServiceImpl extends ServiceImpl<ActvReviewMapper,ActvReview> implements ActvReviewService {

    @Autowired
    private CmtyActvUserService cmtyActvUserService;
    @Autowired
    private  SysUserService sysUserService;
    @Autowired
    private  ActvService actvService;

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
            //申请用户的状态为审批中
            cmtyActvUser.setStatus(1);
            this.updateById(actvReview);
            //指向下一个审批人
            actvReview.setStatus(1);
            actvReview.setId(null);
            //下一个审批人
            if (userId == 1){
                actvReview.setReviewId(2L);
                cmtyActvUser.setReviewId(2L);
                this.save(actvReview);
            }else if (userId == 2){
                actvReview.setReviewId(3L);
                cmtyActvUser.setReviewId(3L);
                this.save(actvReview);
            //通过后状态为已通过，审核人id变为0
            }else{
                cmtyActvUser.setStatus(2);
                cmtyActvUser.setReviewId(0L);
            }
            cmtyActvUserService.updateById(cmtyActvUser);
        //不同意操作
        }else{
            //不同意操作写入原因状态更改为不通过
            ActvReview actvReview = this.getOne(eq);
            actvReview.setStatus(2);
            actvReview.setReason(dto.getReason());
            this.updateById(actvReview);
            CmtyActvUser cmtyActvUser = cmtyActvUserService.getById(dto.getCmtyActvUserId());
            cmtyActvUser.setStatus(3);
            cmtyActvUser.setReason(dto.getReason());
            cmtyActvUserService.updateById(cmtyActvUser);
        }
        getTodo(userId,session);
    }

    public void getTodo(Long userId,HttpSession session){
        //该角色的待办事项
        List<ReviewVo> reviewVos = new ArrayList<>();
        List<ActvReview> reviewList = this.lambdaQuery().eq(ActvReview::getReviewId, userId).eq(ActvReview::getStatus,1).list();
        System.out.println(reviewList);
        reviewList.forEach(review->{
            ReviewVo reviewVo = new ReviewVo();
            BeanUtil.copyProperties(review,reviewVo);
            SysUser user = sysUserService.getById(review.getUserId());
            Actv actv = actvService.getById(review.getActvId());
            reviewVo.setUsername(user.getUsername());
            reviewVo.setActvTitle(actv.getActvTitle());
            reviewVos.add(reviewVo);
        });
        session.setAttribute("reviewList",reviewVos);
    }
}
