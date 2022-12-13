package com.crs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crs.dao.CmtyActvUserMapper;
import com.crs.dto.ApplyDto;
import com.crs.entity.ActvReview;
import com.crs.entity.CmtyActvUser;
import com.crs.service.ActvReviewService;
import com.crs.service.CmtyActvUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author LZ
 * @date 2022-12-02 16:58:23
 */
@Service
@RequiredArgsConstructor
public class CmtyActvUserServiceImpl extends ServiceImpl<CmtyActvUserMapper,CmtyActvUser> implements CmtyActvUserService {
    private final ActvReviewService actvReviewService;

    @Override
    public void add(ApplyDto dto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        CmtyActvUser cmtyActvUser = new CmtyActvUser();
        cmtyActvUser.setActvId(dto.getActvId()).setCmtyId(dto.getCmtyId()).setUserId(userId).setReviewId(1L);
        LambdaQueryWrapper<CmtyActvUser> eq = new QueryWrapper<CmtyActvUser>().lambda().eq(CmtyActvUser::getUserId, userId)
                .eq(CmtyActvUser::getActvId, dto.getActvId()).eq(CmtyActvUser::getCmtyId, dto.getCmtyId());
        if (null == this.getOne(eq)) {
            cmtyActvUser.setStatus(0);
            this.save(cmtyActvUser);
            //添加第一步审核人（社团管理员）
            ActvReview actvReview = new ActvReview();
            actvReview.setActvId(dto.getActvId()).setReviewId(1L).setStatus(1).setUserId(userId);
            actvReviewService.save(actvReview);
        }else{
            this.remove(eq);
        }
    }
}
