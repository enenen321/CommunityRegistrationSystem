package com.crs.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crs.common.annotation.SystemLog;
import com.crs.dto.LoginFormDto;
import com.crs.dto.RegisterDto;
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
import java.nio.charset.StandardCharsets;

/**
 * @author LZ
 * @date 2022/12/03 10:33
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/base")
public class BaseController {

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
        return new ModelAndView("redirect:/base/index");
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
        SysUser sysUser = sysUserService.getOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getSno, form.getSNo()));
        if (null == sysUser){
            session.setAttribute("msg","学号错误或未注册");
            return modelAndView;
        }
        //密码解密验证
        SymmetricCrypto symmetricCrypto = new SymmetricCrypto(SymmetricAlgorithm.DES,sysUser.getSalt().getBytes(StandardCharsets.UTF_8));
        String pwd = symmetricCrypto.decryptStr(sysUser.getPassword());
        if (!form.getPassword().equals(pwd)){
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

    /**
     * 跳转到注册页
     */
    @GetMapping("/register-page")
    public ModelAndView registerPage(){
        return new ModelAndView("front/register");
    }

    /**
     * 注册失败清空缓存重新跳转
     */
    @GetMapping("/register-reset")
    public ModelAndView registerReset(HttpServletRequest request){
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        String msg = session.getAttribute("msg").toString();
        if ("注册成功！".equals(msg)){
            session.invalidate();
            modelAndView.setViewName("redirect:/base/index");
            return modelAndView;
        }else {
            session.invalidate();
            modelAndView.setViewName("front/register");
            return modelAndView;
        }
    }

    /**
     * 用户注册
     */
    @SystemLog(message = "用户注册")
    @PostMapping("/register")
    public ModelAndView register(RegisterDto dto, HttpServletRequest request){
        HttpSession session = request.getSession();
        SysUser byPhone = sysUserService.lambdaQuery().eq(SysUser::getPhone, dto.getPhone()).one();
        SysUser bySno = sysUserService.lambdaQuery().eq(SysUser::getSno, dto.getSno()).one();
        if (null != byPhone){
            session.setAttribute("msg","该手机号已注册");
            return new ModelAndView("redirect:/base/register-page");
        }
        if (null != bySno){
            session.setAttribute("msg","该学号已注册");
            return new ModelAndView("redirect:/base/register-page");
        }
        //密码加密
        SysUser sysUser = new SysUser();
        BeanUtil.copyProperties(dto,sysUser);
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        SymmetricCrypto symmetricCrypto = new SymmetricCrypto(SymmetricAlgorithm.DES, salt.getBytes(StandardCharsets.UTF_8));
        String pwd = symmetricCrypto.encryptHex(dto.getPassword());
        //保存用户
        sysUser.setSalt(salt);
        sysUser.setPassword(pwd);
        sysUser.setAvatar("default.png");
        boolean userSave = sysUserService.save(sysUser);
        //绑定用户和角色关系
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setRoleId(dto.getRoleId());
        sysUserRole.setUserId(sysUser.getId());
        boolean userRoleSave = sysUserRoleService.save(sysUserRole);
        if (userSave && userRoleSave){
            session.setAttribute("msg","注册成功！");
            return new ModelAndView("redirect:/base/index");
        }
        session.setAttribute("msg","注册失败！");
        return new ModelAndView("redirect:/base/register-page");
    }
}
