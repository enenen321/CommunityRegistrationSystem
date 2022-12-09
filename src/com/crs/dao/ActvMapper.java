package com.crs.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crs.entity.Actv;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author LZ
 * @date 2022-12-02 16:58:49
 */
@Mapper
public interface ActvMapper extends BaseMapper<Actv> {

    Actv getActvList(@Param("actv") Actv actv);
}

