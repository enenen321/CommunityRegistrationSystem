package com.crs.dto;

import lombok.Data;

/**
 * @author LZ
 * @date 2022/12/09 13:51
 */
@Data
public class UserRoleDto {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 角色id
     */
    private Long roleId;
}
