package com.crs.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crs.common.annotation.SystemLog;
import com.crs.entity.SysCmty;
import com.crs.service.SysCmtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LZ
 * @date 2022-12-02 16:58:10
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sysCmty")
public class SysCmtyController {

    private final SysCmtyService sysCmtyService;

    /**
     * 分页查询
     * @param sysCmty 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public ResponseEntity<Page<SysCmty>> queryByPage(SysCmty sysCmty) {
        return null;
    }

    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public ResponseEntity<SysCmty> queryById(@PathVariable("id") Long id) {
        return null;
    }

    /**
     * 新增数据
     * @param sysCmty 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    @SystemLog(message = "创建社团")
    public ModelAndView add(SysCmty sysCmty,HttpServletRequest request) {
        return sysCmtyService.add(sysCmty,request);
    }

    /**
     * 编辑数据
     * @param sysCmty 实体
     * @return 编辑结果
     */
    @PutMapping("/update")
    public ResponseEntity<SysCmty> edit(SysCmty sysCmty) {
        return null;
    }

    /**
     * 删除数据
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(Long id) {
        return null;
    }

    /**
     * 跳转到创建社团的页面
     */
    @GetMapping("/createCmt")
    public ModelAndView createCmty(HttpServletRequest request){
      return sysCmtyService.createCmty(request);
    }

    /**
     * 清空缓存防止无限弹窗返回社团创建页
     */
    @GetMapping("/createCmt-reset")
    public ModelAndView createCmtReset(HttpServletRequest request){
        return sysCmtyService.createCmtyReset(request);
    }
}

