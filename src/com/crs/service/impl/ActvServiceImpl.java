package com.crs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crs.dao.ActvMapper;
import com.crs.entity.Actv;
import com.crs.entity.SysCmty;
import com.crs.entity.SysUser;
import com.crs.service.ActvService;
import com.crs.service.SysCmtyService;
import com.crs.service.SysCollService;
import com.crs.service.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author LZ
 * @date 2022-12-02 16:58:49
 */
@Service
@RequiredArgsConstructor
public class ActvServiceImpl extends ServiceImpl<ActvMapper, Actv> implements ActvService {
    private final SysCmtyService sysCmtyService;
    private final SysUserService sysUserService;
    private final SysCollService sysCollService;

    @Override
    public ModelAndView actvList(Integer pn,Actv actv,Model model,HttpServletRequest request) {
        //如果是社团管理员展示所有学院的社团活动，如果不是，则根据用户所属学院展示
        HttpSession session = request.getSession();
        session.removeAttribute("msg");
        Long roleId = (Long) session.getAttribute("roleId");
        Long userId = (Long) session.getAttribute("userId");
        SysUser sysUser = sysUserService.getById(userId);
        PageHelper.startPage(pn,5);
        List<Actv> actvList = this.baseMapper.getActvList(actv, userId, roleId,sysUser.getCollId());
        PageInfo<Actv> actvPageInfo = new PageInfo<>(actvList);
        model.addAttribute("pageInfo",actvPageInfo);
        model.addAttribute("actvList",actvList);
        return new ModelAndView("front/actvlist");
    }

    @Override
    public ModelAndView createActv(HttpServletRequest request) {
        List<SysCmty> sysCmties = sysCmtyService.list();
        HttpSession session = request.getSession();
        session.setAttribute("cmtyList",sysCmties);
        return new ModelAndView("front/actvcreate");
    }

    @Override
    public ModelAndView add(Actv actv,HttpServletRequest request) {
        HttpSession session = request.getSession();
        Actv one = this.lambdaQuery().eq(Actv::getActvTitle, actv.getActvTitle()).eq(Actv::getIsClosed, 0).one();
        ModelAndView modelAndView = new ModelAndView("front/actvcreate");
        if (null != one){
            session.setAttribute("msg","该主题活动已存在！");
            return modelAndView;
        }
        boolean save = this.save(actv);
        if (!save){
            session.setAttribute("msg","未知异常，创建失败！");
            return modelAndView;
        }
        session.setAttribute("msg","创建成功！");
        return modelAndView;
    }

}
