package com.crs.controller;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crs.entity.SysLog;
import com.crs.service.SysLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @author LZ
 * @date 2022-12-02 11:24:39
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sysLog")
public class SysLogController{

    private final SysLogService sysLogService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param sysLog 查询实体
     * @return 所有数据
     */
    @GetMapping("/list")
    public ModelAndView selectAll(Page<SysLog> page, SysLog sysLog) {
        return null;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public ModelAndView selectOne(@PathVariable Serializable id) {
        return null;
    }

    /**
     * 新增数据
     *
     * @param sysLog 实体对象
     * @return 新增结果
     */
    @PostMapping("/insert")
    public ModelAndView insert(@RequestBody SysLog sysLog) {
        return null;
    }

    /**
     * 修改数据
     *
     * @param sysLog 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    public ModelAndView update(@RequestBody SysLog sysLog) {
        return null;
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    public ModelAndView delete(@RequestParam("idList") List<Long> idList) {
        return null;
    }
}

