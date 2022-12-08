package com.crs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crs.dao.ActvMapper;
import com.crs.entity.Actv;
import com.crs.entity.SysCmty;
import com.crs.service.ActvService;
import com.crs.service.SysCmtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    @Override
    public ModelAndView createActv(HttpServletRequest request) {
        List<SysCmty> sysCmties = sysCmtyService.list();
        HttpSession session = request.getSession();
        session.setAttribute("cmtyList",sysCmties);
        return new ModelAndView("front/actvcreate");
    }

    @Override
    public ModelAndView add(Actv actv, HttpServletRequest request) {
        HttpSession session = request.getSession();
        boolean save = this.save(actv);
        ModelAndView modelAndView = new ModelAndView("front/actvcreate");
        if (!save){
            session.setAttribute("msg","未知异常，创建失败！");
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
