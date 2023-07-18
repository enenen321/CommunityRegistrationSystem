package com.crs.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crs.dao.SysUserMapper;
import com.crs.entity.SysColl;
import com.crs.entity.SysUser;
import com.crs.model.UserListModel;
import com.crs.service.SysCollService;
import com.crs.service.SysUserService;
import com.crs.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;


/**
 * @author LZ
 * @date 2022-12-02 10:19:03
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl  extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final SysCollService sysCollService;

    public void upload(MultipartFile multipartFile,Long userId, HttpServletRequest request){
        //图片后缀
        String filename = multipartFile.getOriginalFilename();
        String name = filename.substring(filename.lastIndexOf("."));
        //用户名+时间戳+图片后缀名，便于浏览器识别及时加载
        String avatar = userId +"_"+ System.currentTimeMillis() + name;
        HttpSession session = request.getSession();
        //删除缓存重载头像并删除项目路径原来图片
        session.removeAttribute("avatar");
        //保存到项目路径下，并且保存到资源路径便于及时展示
        String contextPath = request.getContextPath().replaceAll("/","");
        String path = this.getClass().getResource("/").getPath();
        String projectPath = path.substring(1,path.length() - 16);
        int index = path.indexOf(contextPath);
        path = path.substring(1, index + contextPath.length());
        try {
            InputStream inputStream = multipartFile.getInputStream();
            //本地资源路径，图片先保存到项目，再从本地项目路径读取流加载到资源，不然重启服务后图片资源无法加载
            File file = new File(path + "/web/resource/images/avatars/" + avatar);
            BufferedOutputStream outputStream = FileUtil.getOutputStream(file);
            //项目路径copy
            IoUtil.copy(inputStream, outputStream);
            IoUtil.close(outputStream);
            //从项目路径读取流加载到资源路径
            BufferedInputStream localFileInputStream = FileUtil.getInputStream(file);
            File imgResource = new File(projectPath + "resource/images/avatars/" + avatar);
            BufferedOutputStream resource = FileUtil.getOutputStream(imgResource);
            IoUtil.copy(localFileInputStream,resource);
            //关闭流
            IoUtil.close(resource);
            IoUtil.close(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //更新用户
        SysUser sysUser = this.getById(userId);
        File file = new File(path + "/web/resource/images/avatars/" + sysUser.getAvatar());
        if (file.exists() && !"default.png".equals(sysUser.getAvatar())){
             file.delete();
        }
        sysUser.setAvatar(avatar)
                .setId(userId);
        this.updateById(sysUser);
        session.setAttribute("avatar",avatar);
    }

    @Override
    public ModelAndView userList(Integer pn, UserListModel userModel, HttpServletRequest request) {
        HttpSession session = request.getSession();
        PageHelper.startPage(pn,5);
        List<UserListModel> userListModels = this.baseMapper.userList(userModel);
        PageInfo<UserListModel> pageInfo = new PageInfo(userListModels);
        session.setAttribute("pageInfo",pageInfo);
        session.setAttribute("users",userListModels);
        return new ModelAndView("front/sysuser");
    }

    @Override
    public ModelAndView userDetail(Long userId,HttpServletRequest request) {
        SysUser sysUser = this.getById(userId);
        UserVo vo = new UserVo();
        BeanUtil.copyProperties(sysUser,vo);
        SysColl coll = sysCollService.getById(sysUser.getCollId());
        vo.setCollName(coll.getCollName());
        request.setAttribute("avatar",sysUser.getAvatar());
        request.setAttribute("userdetail",vo);
        return new ModelAndView("front/userdetail");
    }

}
