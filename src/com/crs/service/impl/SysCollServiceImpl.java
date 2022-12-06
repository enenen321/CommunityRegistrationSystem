package com.crs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crs.dao.SysCollMapper;
import com.crs.entity.SysColl;
import com.crs.service.SysCollService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author LZ
 * @date 2022-12-02 16:58:49
 */
@Service
@RequiredArgsConstructor
public class SysCollServiceImpl extends ServiceImpl<SysCollMapper, SysColl> implements SysCollService {

}
