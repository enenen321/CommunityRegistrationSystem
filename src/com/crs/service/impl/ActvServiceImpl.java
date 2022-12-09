package com.crs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crs.dao.ActvMapper;
import com.crs.entity.Actv;
import com.crs.entity.SysCmty;
import com.crs.entity.SysColl;
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
import java.util.ArrayList;
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
        Long roleId = (Long) session.getAttribute("roleId");
        Long userId = (Long) session.getAttribute("userId");
        List<Actv> list;
        List<Long> cmtyIds = new ArrayList<>();
        if (roleId != 1){
            //找到用户所属学院
            SysUser user = sysUserService.getById(userId);
            //根据学院找到对应社团
            // TODO: 2022/12/9 这串语句需要用sql写 方便模糊查询
            List<SysColl> collList = sysCollService.lambdaQuery().eq(SysColl::getId,user.getCollId()).list();
            //社团id
            collList.forEach(sysColl -> cmtyIds.add(sysColl.getId()));
            //该学院的所有活动
            //这句只针对其下一查询语句生效
            PageHelper.startPage(pn,5);
            list = this.lambdaQuery().eq(Actv::getIsClosed, 0).in(Actv::getCmtyId,cmtyIds).list();
        }else{
            PageHelper.startPage(pn,5);
            list = this.lambdaQuery().eq(Actv::getIsClosed,0).list();
        }
        list.forEach(l->{
            SysCmty cmty = sysCmtyService.getById(l.getCmtyId());
            SysColl coll = sysCollService.getById(cmty.getCollId());
            l.setCmtyName(cmty.getCmtyName());
            l.setCollName(coll.getCollName());
        });
        PageInfo<Actv> actvPageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",actvPageInfo);
        model.addAttribute("actvList",list);
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

    @Override
    public ModelAndView createActvReset(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("msg");
        return new ModelAndView("front/actvcreate");
    }

}
