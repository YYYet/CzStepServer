package com.chengzzz.stepservice.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengzzz.stepservice.dao.UpstepsDao;
import com.chengzzz.stepservice.entity.Upsteps;
import com.chengzzz.stepservice.service.UpstepsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Upsteps)表控制层
 *
 * @author 等什么柠檬君
 * @since 2020-08-21 20:56:47
 */
@RestController
@RequestMapping("upsteps")
public class UpstepsController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private UpstepsService upstepsService;

    @Autowired
    private UpstepsDao upstepsDao;

    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param upsteps 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<Upsteps> page, Upsteps upsteps) {
        return success(this.upstepsService.page(page, new QueryWrapper<>(upsteps)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.upstepsService.getById(id));
    }
/**
 * 
 * @param phone phone 
 * @return java.util.List
 * @author 等什么柠檬君
 * @since 2020/8/21
 * @description    
 */
    @GetMapping("{phone}")
    public List selectByPhone(String phone) {
        QueryWrapper<Upsteps> wrapper=new QueryWrapper<>();
        wrapper.eq("phone",phone);
        return upstepsDao.selectList(wrapper);
    }

    /**
     * 新增数据
     *
     * @param upsteps 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Upsteps upsteps) {
        return success(this.upstepsService.save(upsteps));
    }

    /**
     * 修改数据
     *
     * @param upsteps 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Upsteps upsteps) {
        return success(this.upstepsService.updateById(upsteps));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.upstepsService.removeByIds(idList));
    }
}