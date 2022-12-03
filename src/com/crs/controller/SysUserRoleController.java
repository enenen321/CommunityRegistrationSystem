package com.crs.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crs.entity.SysUserRole;
import com.crs.service.SysUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author LZ
 * @date 2022-12-02 16:57:58
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("sysUserRole")
public class SysUserRoleController {

    private final SysUserRoleService sysUserRoleService;

    /**
     * 分页查询
     * @param sysUserRole 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public ResponseEntity<Page<SysUserRole>> queryByPage(SysUserRole sysUserRole) {
        return null;
    }

    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public ResponseEntity<SysUserRole> queryById(@PathVariable("id") Long id) {
        return null;
    }

    /**
     * 新增数据
     * @param sysUserRole 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public ResponseEntity<SysUserRole> add(SysUserRole sysUserRole) {
        return null;
    }

    /**
     * 编辑数据
     * @param sysUserRole 实体
     * @return 编辑结果
     */
    @PutMapping("/update")
    public ResponseEntity<SysUserRole> edit(SysUserRole sysUserRole) {
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

