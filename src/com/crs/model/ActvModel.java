package com.crs.model;

import com.crs.entity.Actv;
import lombok.Data;

/**
 * @author LZ
 * @date 2022/12/12 15:31
 */
@Data
public class ActvModel extends Actv {
    /**
     * 所属学院名称
     */
    private String collName;
    /**
     * 所属社团名称
     */
    private String cmtyName;
    /**
     * 社团id
     */
    private Long cmtyId;
    /**
     * 是否已报名
     */
    private Integer isApply = 0;

}
