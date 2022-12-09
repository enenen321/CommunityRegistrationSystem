package com.crs.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.crs.common.mybati.base.BaseEntity;
import lombok.Data;

/**
 * @author LZ
 * @date 2022-12-02 16:58:49
 */
@Data
public class Actv extends BaseEntity {

    /**
     * 活动主题
     */
    private String actvTitle;
    /**
     * 活动内容
     */
    private String actvContent;
    /**
     * 社团id
     */
    private Long cmtyId;
    /**
     * 报名截止日期
     */
    private String deadline;
    /**
     * 是否关闭
     */
    private Integer isClosed = 0;
    /**
     * 所属学院名称
     */
    @TableField(exist = false)
    private String collName;
    /**
     * 所属社团名称
     */
    @TableField(exist = false)
    private String cmtyName;
}

