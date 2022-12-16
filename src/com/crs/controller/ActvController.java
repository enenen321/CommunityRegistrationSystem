package com.crs.controller;

import com.crs.common.annotation.SystemLog;
import com.crs.entity.Actv;
import com.crs.service.ActvService;
import com.crs.model.ActvModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
     * @return 查询结果
     */
    @GetMapping("/list/{pn}")
    @SystemLog(message = "查看活动列表")
    public ModelAndView list(@PathVariable("pn") Integer pn, ActvModel actv, Model model, HttpServletRequest request) {
        return actvService.actvList(pn,actv,model,request);
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
    @SystemLog(message = "添加社团活动")
    public ModelAndView add(@RequestBody Actv actv,HttpServletRequest request) {
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
}

