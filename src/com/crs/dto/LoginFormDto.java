package com.crs.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author LZ
 * @date 2022/12/03 12:06
 */
@Data
public class LoginFormDto implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 学号
     */
    private String sNo;
    /**
     * 密码
     */
    private String password;
}
