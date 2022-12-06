package com.crs.dto;

import com.crs.entity.SysUser;
import lombok.Data;

/**
 * @author LZ
 * @date 2022/12/06 11:30
 */
@Data
public class RegisterDto extends SysUser {
    /**
     * 角色
     */
    private Long roleId;
}
