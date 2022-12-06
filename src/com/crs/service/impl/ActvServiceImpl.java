package com.crs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crs.entity.Actv;
import com.crs.dao.ActvMapper;
import com.crs.service.ActvService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author LZ
 * @date 2022-12-02 16:58:49
 */
@Service
@RequiredArgsConstructor
public class ActvServiceImpl extends ServiceImpl<ActvMapper, Actv> implements ActvService {

}
