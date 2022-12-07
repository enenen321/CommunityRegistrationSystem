package com.crs.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author LZ
 * @date 2022/12/07 13:50
 */
@Data
@Accessors(chain = true)
public class UserVo {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户名称
     */
    private String username;
}
