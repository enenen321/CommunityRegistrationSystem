package com.crs.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crs.entity.SysRoleMenu;
import com.crs.service.SysRoleMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @author LZ
 * @date 2022-12-02 16:57:46
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("sysRoleMenu")
public class SysRoleMenuController {

    private final SysRoleMenuService sysRoleMenuService;

    /**
     * 分页查询
     * @param sysRoleMenu 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public ResponseEntity<Page<SysRoleMenu>> queryByPage(SysRoleMenu sysRoleMenu) {
        return null;
    }

    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public ResponseEntity<SysRoleMenu> queryById(@PathVariable("id") Long id) {
        return null;
    }

    /**
     * 新增数据
     * @param sysRoleMenu 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public ResponseEntity<SysRoleMenu> add(SysRoleMenu sysRoleMenu) {
        return null;
    }

    /**
     * 编辑数据
     * @param sysRoleMenu 实体
     * @return 编辑结果
     */
    @PutMapping("/update")
    public ResponseEntity<SysRoleMenu> edit(SysRoleMenu sysRoleMenu) {
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

