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
        SysUser sysUser = this.getById(userId);
        //图片后缀
        String filename = multipartFile.getOriginalFilename();
        String name = filename.substring(filename.lastIndexOf("."));
        //头像名
        String avatar = userId+name;
        HttpSession session = request.getSession();
        //删除缓存重载头像并删除本地原来图片
        session.removeAttribute("avatar");
        String contextPath = request.getContextPath().replaceAll("/","");
        String path = this.getClass().getResource("/").getPath();
        int index = path.indexOf(contextPath);
        path = path.substring(1, index + contextPath.length());
        try {
            InputStream inputStream = multipartFile.getInputStream();
            File file = new File(path + "/web/resource/images/avatars/" + avatar);
            //删除原来头像，写入新头像
            File oldFile = new File(path + "/web/resource/images/avatars/"+sysUser.getAvatar());
            if (oldFile.exists()) {
                //默认图片不删除
                if (!"default.png".equals(sysUser.getAvatar())) {
                    oldFile.delete();
                }
            }
            BufferedOutputStream outputStream = FileUtil.getOutputStream(file);
            IoUtil.copy(inputStream, outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //更新用户
        SysUser user = new SysUser();
        user.setAvatar(avatar)
                .setId(userId);
        this.updateById(user);
        session.setAttribute("avatar",avatar);
    }

}
