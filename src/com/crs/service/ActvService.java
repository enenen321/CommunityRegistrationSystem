package com.crs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crs.entity.Actv;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LZ
 * @date 2022-12-02 16:58:49
 */
public interface ActvService extends IService<Actv> {

    /**
     * 社团活动列表
     */
    ModelAndView actvList(Integer pn,Actv actv, Model model,HttpServletRequest request);

    /**
     * 跳转到创建社团页面
     */
    ModelAndView createActv(HttpServletRequest request);

    /**
     * 添加社团活动
     */
    ModelAndView add(Actv dto,HttpServletRequest request);

    /**
     * 请求完后清楚缓存跳转
     */
    ModelAndView createActvReset(HttpServletRequest request);

}
