package com.crs.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crs.entity.SysUser;
import com.crs.model.UserListModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LZ
 * @date 2022-12-02 10:10:05
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 查询所有用户信息
     */
    List<UserListModel> userList(@Param("userModel") UserListModel userModel);
}

