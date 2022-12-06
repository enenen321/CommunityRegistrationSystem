package com.crs.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crs.entity.SysColl;
import com.crs.service.SysCollService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author LZ
 * @date 2022-12-02 16:58:49
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sysColl")
public class SysCollController {

    private final SysCollService sysCollService;

    /**
     * 分页查询
     * @param sysColl 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public ResponseEntity<Page<SysColl>> queryByPage(SysColl sysColl) {
        return null;
    }

    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public ResponseEntity<SysColl> queryById(@PathVariable("id") Long id) {
        return null;
    }

    /**
     * 新增数据
     * @param sysColl 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public ResponseEntity<SysColl> add(SysColl sysColl) {
        return null;
    }

    /**
     * 编辑数据
     * @param sysColl 实体
     * @return 编辑结果
     */
    @PutMapping("/update")
    public ResponseEntity<SysColl> edit(SysColl sysColl) {
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

}

