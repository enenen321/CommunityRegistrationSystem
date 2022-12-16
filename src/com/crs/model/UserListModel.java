package com.crs.model;

import lombok.Data;

/**
 * @author LZ
 * @date 2022/12/16 10:21
 */
@Data
public class UserListModel {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 学院名称
     */
    private String collName;
    /**
     * 用户学号
     */
    private String sno;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 联系号码
     */
    private String phone;
    /**
     * 用户头像
     */
    private String avatar;
}
