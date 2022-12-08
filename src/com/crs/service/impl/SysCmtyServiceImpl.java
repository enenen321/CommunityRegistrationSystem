package com.crs.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crs.dao.SysCmtyMapper;
import com.crs.entity.SysCmty;
import com.crs.entity.SysUser;
import com.crs.entity.SysUserRole;
import com.crs.service.SysCmtyService;
import com.crs.service.SysUserRoleService;
import com.crs.service.SysUserService;
import com.crs.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LZ
 * @date 2022-12-02 16:58:10
 */
@Service
@RequiredArgsConstructor
public class SysCmtyServiceImpl extends ServiceImpl<SysCmtyMapper,SysCmty> implements SysCmtyService {
    private final SysUserRoleService sysUserRoleService;
    private final SysUserService sysUserService;

    @Override
    public ModelAndView createCmty(HttpServletRequest request){
        List<SysUserRole> sysUserRoles = sysUserRoleService.lambdaQuery().notIn(SysUserRole::getRoleId, 1, 2, 3).list();
        List<UserVo> userVoList = new ArrayList<>();
        if(null != sysUserRoles){
            List<Long> user = new ArrayList<>();
            sysUserRoles.forEach(sysUserRole -> user.add(sysUserRole.getUserId()));
            if (user.size() != 0) {
                List<SysUser> list = sysUserService.lambdaQuery().in(SysUser::getId, user).list();
                list.forEach(userList -> {
                    UserVo userVo = new UserVo();
                    userVo.setUserId(userList.getId()).setUsername(userList.getUsername());
                    userVoList.add(userVo);
                });
            }
        }
        HttpSession session = request.getSession();
        session.setAttribute("userList",userVoList);
        return new ModelAndView("front/cmtyCreate");
    }

    @Override
    public ModelAndView add(SysCmty cmty,HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView("front/cmtyCreate");
        SysCmty one = this.lambdaQuery().eq(SysCmty::getCollId, cmty.getCollId()).eq(SysCmty::getCmtyName, cmty.getCmtyName()).one();
        if (null != one){
            session.setAttribute("msg","该社团已存在");
            return modelAndView;
        }
        boolean save = this.save(cmty);
        boolean update = sysUserRoleService.update(new UpdateWrapper<SysUserRole>().set("role_id", 2).eq("user_id", cmty.getManagerId()));
        if (!save && !update){
            session.setAttribute("msg","未知异常，创建失败！");
            return modelAndView;
        }
        session.setAttribute("msg","创建成功！");
        return modelAndView;
    }

    @Override
    public ModelAndView createCmtyReset(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("msg");
        return this.createCmty(request);
    }
}
