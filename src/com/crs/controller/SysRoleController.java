package com.crs.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crs.entity.SysRole;
import com.crs.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author LZ
 * @date 2022-12-02 16:57:04
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("sysRole")
public class SysRoleController {

    private final SysRoleService sysRoleService;

    /**
     * 分页查询
     * @param sysRole 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public ResponseEntity<Page<SysRole>> queryByPage(SysRole sysRole) {
        return null;
    }

    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public ResponseEntity<SysRole> queryById(@PathVariable("id") Long id) {
        return null;
    }

    /**
     * 新增数据
     * @param sysRole 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public ResponseEntity<SysRole> add(SysRole sysRole) {
        return null;
    }

    /**
     * 编辑数据
     * @param sysRole 实体
     * @return 编辑结果
     */
    @PutMapping("/update")
    public ResponseEntity<SysRole> edit(SysRole sysRole) {
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

