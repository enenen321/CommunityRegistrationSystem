package com.crs.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crs.common.annotation.SystemLog;
import com.crs.dto.LoginFormDto;
import com.crs.entity.SysUser;
import com.crs.entity.SysUserRole;
import com.crs.service.SysUserRoleService;
import com.crs.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author LZ
 * @date 2022/12/03 10:33
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/loginOrLogout")
public class LoginOrLogoutController {

    private final SysUserService sysUserService;
    private final SysUserRoleService sysUserRoleService;


    /**
     * 关闭或注销返回首页
     */
    @GetMapping("/back")
    public ModelAndView back(HttpServletRequest request){
        HttpSession session = request.getSession();
        //清空缓存
        session.invalidate();
        //跳转到登录页面
        return new ModelAndView("redirect:/loginOrLogout/index");
    }
    /**
     * 跳转到登录页面
     */
    @GetMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("front/login");
    }


    /**
     * 登录验证
     */
    @PostMapping("/crs")
    @SystemLog(message = "登录系统")
    public ModelAndView login(LoginFormDto form, HttpServletRequest request){
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView("/front/login");
        //判断学号是否存在
        SysUser sysUser = sysUserService.getOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getUsername, form.getSNo()));
        if (null == sysUser){
            session.setAttribute("msg","学号错误或未注册");
            return modelAndView;
        }
        //密码验证
        if (!form.getPassword().equals(sysUser.getPassword())){
            session.setAttribute("msg","密码错误");
            return modelAndView;
        }
        //查找角色id
        SysUserRole role = sysUserRoleService.lambdaQuery().eq(SysUserRole::getUserId, sysUser.getId()).one();
        session.setAttribute("roleId",role.getRoleId());
        session.setAttribute("avatar",sysUser.getAvatar());
        session.setAttribute("username",sysUser.getUsername());
        session.setAttribute("userId",sysUser.getId());
        modelAndView.setViewName("front/homepage");
        return modelAndView;
    }
}
