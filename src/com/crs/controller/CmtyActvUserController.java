package com.crs.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crs.dto.ApplyDto;
import com.crs.entity.CmtyActvUser;
import com.crs.service.CmtyActvUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LZ
 * @date 2022-12-02 16:58:23
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("cmtyActvUser")
public class CmtyActvUserController {

    private final CmtyActvUserService cmtyActvUserService;

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
    public void add(ApplyDto dto, HttpServletRequest request) {
        cmtyActvUserService.add(dto,request);
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

