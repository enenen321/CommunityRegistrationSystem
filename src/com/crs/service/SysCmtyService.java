package com.crs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crs.entity.SysCmty;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LZ
 * @date 2022-12-02 16:58:10
 */
public interface SysCmtyService extends IService<SysCmty> {

    ModelAndView createCmty(HttpServletRequest request);

    ModelAndView add(SysCmty cmty,HttpServletRequest request);
}
