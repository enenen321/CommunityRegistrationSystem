package com.crs.entity;

import com.crs.common.mybati.base.BaseEntity;
import lombok.Data;


/**
 * @author LZ
 * @date 2022-12-02 11:24:42
 */
@Data
public class SysLog extends BaseEntity {

    /**
     * 具体信息
     */
    private String message;
    /**
     * 0 正常 1 异常
     */
    private Integer status;
    /**
     * 返回结果
     */
    private String result;
    /**
     * 花费时间 ms
     */
    private Long time;
    /**
     * 接口url
     */
    private String url;
    /**
     * 访问方ip
     */
    private String ip;
    /**
     * 异常信息
     */
    private String exception;
}

