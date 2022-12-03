package com.crs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crs.entity.CmtyActvUser;
import com.crs.dao.CmtyActvUserMapper;
import com.crs.service.CmtyActvUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author LZ
 * @date 2022-12-02 16:58:23
 */
@Service
@RequiredArgsConstructor
public class CmtyActvUserServiceImpl extends ServiceImpl<CmtyActvUserMapper,CmtyActvUser> implements CmtyActvUserService {

}
