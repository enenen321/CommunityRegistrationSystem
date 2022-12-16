package com.crs.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crs.common.annotation.SystemLog;
import com.crs.dto.ReviewDto;
import com.crs.entity.ActvReview;
import com.crs.service.ActvReviewService;
import com.crs.vo.ReviewVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author LZ
 * @date 2022-12-02 16:58:33
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("actvReview")
public class ActvReviewController {

    private final ActvReviewService actvReviewService;

    /**
     * 分页查询
     * @param actvReview 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public ResponseEntity<Page<ActvReview>> queryByPage(ActvReview actvReview) {
        return null;
    }

    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public ResponseEntity<ActvReview> queryById(@PathVariable("id") Long id) {
        return null;
    }

    /**
     * 新增数据
     * @param actvReview 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public ResponseEntity<ActvReview> add(ActvReview actvReview) {
        return null;
    }

    /**
     * 编辑数据
     * @param actvReview 实体
     * @return 编辑结果
     */
    @PutMapping("/update")
    public ResponseEntity<ActvReview> edit(ActvReview actvReview) {
        return null;
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(Long id) {
        return null;
    }

    /**
     * 跳转到审核页面
     */
    @GetMapping("/reviewList/{pn}")
    @SystemLog(message = "查看待审核列表")
    public ModelAndView reviewList(@PathVariable("pn") Integer pn,HttpServletRequest request){
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        PageHelper.startPage(pn,5);
        List<ReviewVo> reviewVos = actvReviewService.reviewList(request, userId);
        PageInfo<ReviewVo> reviewVoPageInfo = new PageInfo<>(reviewVos);
        session.setAttribute("pageInfo",reviewVoPageInfo);
        session.setAttribute("reviewVos",reviewVos);
        return new ModelAndView("front/reviewlist");
    }

    @PostMapping("/check")
    @SystemLog(message = "审核申请")
    public void check(ReviewDto dto,HttpServletRequest request){
        actvReviewService.check(dto,request);
    }

}

