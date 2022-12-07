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

    private final SysUserRoleService sysUserRoleService;

    private final SysUserService sysUserService;


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
     *
     * @param sysCmty 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public ResponseEntity<SysCmty> add(SysCmty sysCmty) {
        return null;
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
        List<SysUserRole> sysUserRoles = sysUserRoleService.lambdaQuery().notIn(SysUserRole::getRoleId, 1, 2, 3).list();
        List<Long> user = new ArrayList<>();
        sysUserRoles.forEach(sysUserRole -> user.add(sysUserRole.getUserId()));
        List<SysUser> list = sysUserService.lambdaQuery().in(SysUser::getId, user).list();
        List<UserVo> userVoList = new ArrayList<>();
        list.forEach(userList->{
            UserVo userVo = new UserVo();
            userVo.setUserId(userList.getId()).setUsername(userList.getUsername());
            userVoList.add(userVo);
        });
        HttpSession session = request.getSession();
        session.setAttribute("userList",userVoList);
        return new ModelAndView("front/cmtyCreate");
    }
}

