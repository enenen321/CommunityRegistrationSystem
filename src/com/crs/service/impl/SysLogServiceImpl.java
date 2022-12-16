package com.crs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crs.dao.SysLogMapper;
import com.crs.entity.SysLog;
import com.crs.service.SysLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author LZ
 * @date 2022-12-02 11:24:42
 */
@Service
@RequiredArgsConstructor
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Override
    public ModelAndView logList(Integer pn, SysLog sysLog, HttpServletRequest request) {
        HttpSession session = request.getSession();
        PageHelper.startPage(pn,5);
        List<SysLog> list = this.list(new QueryWrapper<SysLog>().lambda().like(null != sysLog.getIp(), SysLog::getIp, sysLog.getIp())
                .orderByDesc(SysLog::getCreatedTime));
        PageInfo<SysLog> pageInfo = new PageInfo<>(list);
        session.setAttribute("pageInfo",pageInfo);
        session.setAttribute("sysLogs",list);
        return new ModelAndView("front/syslog");
    }
}

