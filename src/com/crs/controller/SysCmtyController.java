package com.crs.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crs.entity.SysCmty;
import com.crs.entity.SysUser;
import com.crs.entity.SysUserRole;
import com.crs.service.SysCmtyService;
import com.crs.service.SysUserRoleService;
import com.crs.service.SysUserService;
import com.crs.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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
}

