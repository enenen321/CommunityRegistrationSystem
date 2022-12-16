package com.crs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crs.entity.SysLog;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LZ
 * @date 2022-12-02 11:24:42
 */
public interface SysLogService extends IService<SysLog> {

    ModelAndView logList(Integer pn, SysLog sysLog, HttpServletRequest request);
}

