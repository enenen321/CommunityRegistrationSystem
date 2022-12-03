package com.crs.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crs.dao.SysUserMapper;
import com.crs.entity.SysUser;
import com.crs.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * @author LZ
 * @date 2022-12-02 10:19:03
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl  extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}
