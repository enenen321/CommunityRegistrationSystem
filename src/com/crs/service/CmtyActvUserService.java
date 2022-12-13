package com.crs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crs.dto.ApplyDto;
import com.crs.entity.CmtyActvUser;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LZ
 * @date 2022-12-02 16:58:23
 */
public interface CmtyActvUserService extends IService<CmtyActvUser> {

    void add(ApplyDto dto, HttpServletRequest request);

}
