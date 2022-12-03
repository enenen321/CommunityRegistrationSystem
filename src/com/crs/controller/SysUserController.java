package com.crs.controller;

import com.crs.entity.SysUser;
import com.crs.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author LZ
 * @date 2022-12-02 10:08:37
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sysUser")
public class SysUserController {

    private final SysUserService sysUserService;


    /**
     * 分页查询
     *
     * @param sysUser 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public ModelAndView queryByPage(SysUser sysUser) {

        return new ModelAndView();
    }

    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public ResponseEntity<SysUser> queryById(@PathVariable("id") Long id) {
        return null;
    }

    /**
     * 新增数据
     * @param sysUser 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public ResponseEntity<SysUser> add(SysUser sysUser) {
       return null;
    }

    /**
     * 编辑数据
     * @param sysUser 实体
     * @return 编辑结果
     */
    @PutMapping("/update")
    public ResponseEntity<SysUser> edit(SysUser sysUser) {
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

