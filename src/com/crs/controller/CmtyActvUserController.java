package com.crs.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crs.dto.ApplyDto;
import com.crs.entity.CmtyActvUser;
import com.crs.model.ActvModel;
import com.crs.service.ActvService;
import com.crs.service.CmtyActvUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author LZ
 * @date 2022-12-02 16:58:23
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("cmtyActvUser")
public class CmtyActvUserController {

    private final CmtyActvUserService cmtyActvUserService;
    private final ActvService actvService;

    /**
     * 分页查询
     * @param cmtyActvUser 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public ResponseEntity<Page<CmtyActvUser>> queryByPage(CmtyActvUser cmtyActvUser) {
        return null;
    }

    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public ResponseEntity<CmtyActvUser> queryById(@PathVariable("id") Long id) {
        return null;
    }

    /**
     * 新增数据
     * @return 新增结果
     */
    @PostMapping("/add")
    public ModelAndView add(ApplyDto dto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        CmtyActvUser cmtyActvUser = new CmtyActvUser();
        cmtyActvUser.setActvId(dto.getActvId()).setCmtyId(dto.getCmtyId()).setUserId(userId);
        LambdaQueryWrapper<CmtyActvUser> eq = new QueryWrapper<CmtyActvUser>().lambda().eq(CmtyActvUser::getUserId, userId)
                .eq(CmtyActvUser::getActvId, dto.getActvId()).eq(CmtyActvUser::getCmtyId, dto.getCmtyId());
        if (null == cmtyActvUserService.getOne(eq)) {
            cmtyActvUserService.save(cmtyActvUser);
        }else{
            cmtyActvUserService.remove(eq);
        }
        return actvService.actvList(1,new ActvModel(),new ExtendedModelMap(),request);
    }

    /**
     * 编辑数据
     *
     * @param cmtyActvUser 实体
     * @return 编辑结果
     */
    @PutMapping("/update")
    public ResponseEntity<CmtyActvUser> edit(CmtyActvUser cmtyActvUser) {
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

