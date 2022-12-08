package com.crs.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crs.entity.Actv;
import com.crs.service.ActvService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LZ
 * @date 2022-12-02 16:58:49
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/actv")
public class ActvController {

    private final ActvService actvService;

    /**
     * 分页查询
     * @param actv 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public ResponseEntity<Page<Actv>> queryByPage(Actv actv) {
        return null;
    }

    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public ResponseEntity<Actv> queryById(@PathVariable("id") Long id) {
        return null;
    }

    /**
     * 新增数据
     * @param actv 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public ModelAndView add(@RequestBody Actv actv, HttpServletRequest request) {
        return actvService.add(actv,request);
    }

    /**
     * 编辑数据
     * @param actv 实体
     * @return 编辑结果
     */
    @PutMapping("/update")
    public ResponseEntity<Actv> edit(Actv actv) {
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
     * 跳转到创建社团活动页面
     */
    @GetMapping("/createActv")
    public ModelAndView createActv(HttpServletRequest request){
        return actvService.createActv(request);
    }

    @GetMapping("createActv-reset")
    public ModelAndView createActvRest(HttpServletRequest request){
        return actvService.createActvReset(request);
    }
}

