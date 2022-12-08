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

    /**
     * 跳转到创建社团的页面
     */
    ModelAndView createCmty(HttpServletRequest request);

    /**
     * 新增数据
     * @param cmty 实体
     * @return 新增结果
     */
    ModelAndView add(SysCmty cmty,HttpServletRequest request);

    /**
     * 清空缓存防止无限弹窗返回社团创建页
     */
    ModelAndView createCmtyReset(HttpServletRequest request);
}
