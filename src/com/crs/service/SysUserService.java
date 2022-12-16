package com.crs.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.crs.entity.SysUser;
import com.crs.model.UserListModel;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LZ
 * @date 2022-12-02 10:08:40
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 上传图片文件
     * @param multipartFile 文件
     * @param userId 用户id
     * @param request 请求
     * @return jsp视图模型
     */
     void upload(MultipartFile multipartFile,Long userId, HttpServletRequest request);

    /**
     * 用户列表
     */
    ModelAndView userList(Integer pn, UserListModel userModel, HttpServletRequest request);

}
