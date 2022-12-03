package com.crs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crs.entity.ActvReport;
import com.crs.dao.ActvReportMapper;
import com.crs.service.ActvReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author LZ
 * @date 2022-12-02 16:58:49
 */
@Service
@RequiredArgsConstructor
public class ActvReportServiceImpl extends ServiceImpl<ActvReportMapper,ActvReport> implements ActvReportService {

}
