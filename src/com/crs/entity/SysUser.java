package com.crs.entity;


import com.crs.common.mybati.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * (SysUser)实体类
 *
 * @author LZ
 * @date 2022-12-02 10:05:40
 */
@Data
@Accessors(chain = true)
public class SysUser extends BaseEntity {

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 盐
     */
    private String salt;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 所属学院
     */
    private Long collId;
}

