package com.crs.service.impl;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crs.dao.SysUserMapper;
import com.crs.entity.SysUser;
import com.crs.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


/**
 * @author LZ
 * @date 2022-12-02 10:19:03
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl  extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    public void upload(MultipartFile multipartFile,Long userId, HttpServletRequest request){
        //图片后缀
        String filename = multipartFile.getOriginalFilename();
        String name = filename.substring(filename.lastIndexOf("."));
        //头像名
        String avatar = userId +"_"+ System.currentTimeMillis() + name;
        HttpSession session = request.getSession();
        //删除缓存重载头像并删除本地原来图片
        session.removeAttribute("avatar");
        //保存到项目路径下，并且保存到tomcat服务路径便于及时展示
        String contextPath = request.getContextPath().replaceAll("/","");
        String path = this.getClass().getResource("/").getPath();
        String projectPath = path.substring(1,path.length()-16);
        int index = path.indexOf(contextPath);
        path = path.substring(1, index + contextPath.length());
        try {
            InputStream inputStream = multipartFile.getInputStream();
            //本地资源路径
            File file = new File(path + "/web/resource/images/avatars/" + avatar);
            BufferedOutputStream outputStream = FileUtil.getOutputStream(file);
            //资源加载路径
            File imgResource = new File(projectPath+"resource/images/avatars/" + avatar);
            BufferedOutputStream resource = FileUtil.getOutputStream(imgResource);
            //资源加载路径copy
            IoUtil.copy(inputStream,resource);
            //存到本地路径copy
            IoUtil.copy(inputStream, outputStream);
            IoUtil.close(outputStream);
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

}
