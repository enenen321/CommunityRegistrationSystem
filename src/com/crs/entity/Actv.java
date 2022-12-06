package com.crs.entity;

import com.crs.common.mybati.base.BaseEntity;
import lombok.Data;
import java.util.Date;

/**
 * @author LZ
 * @date 2022-12-02 16:58:49
 */
@Data
public class Actv extends BaseEntity {

    /**
     * 活动名称
     */
    private String actvName;
    /**
     * 社团id
     */
    private Long cmtyId;
    /**
     * 报名截止日期
     */
    private Date deadlineTime;
    /**
     * 是否关闭
     */
    private Integer isClosed;
}

