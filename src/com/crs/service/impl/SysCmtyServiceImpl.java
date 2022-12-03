package com.crs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crs.entity.SysCmty;
import com.crs.dao.SysCmtyMapper;
import com.crs.service.SysCmtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author LZ
 * @date 2022-12-02 16:58:10
 */
@Service
@RequiredArgsConstructor
public class SysCmtyServiceImpl extends ServiceImpl<SysCmtyMapper,SysCmty> implements SysCmtyService {

}
