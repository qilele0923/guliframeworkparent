package com.guli.edu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.entity.Teacher;
import com.guli.edu.query.TeacherQuery;
import com.guli.edu.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.guli.common.vo.R;
import java.util.List;

/**
 * Created by qi on  2020-01-18 17:08
 */
@RestController
@RequestMapping("/admin/edu/teacher")
public class TeacherAdminController {

    @Autowired
    private TeacherService service;


    @GetMapping
    public R list(){
        List<Teacher> list = service.list(null);
        return R.ok().data("items",list);
    }

    @DeleteMapping("{id}")
    public  R removeById( String id){
        service.removeById(id);
        return  R.ok();
    }


    @ApiOperation("分页测试")
    @GetMapping("{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page",value = "当前页",required = true)
            @PathVariable("page") Long page,
            @ApiParam(name = "limit",value = "每页记录数",required = true)
            @PathVariable("limit")   Long limit,
    @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
                    TeacherQuery teacherQuery
    ){
        Page<Teacher> pageParam = new Page<>(page, limit);
        service.pageQuery(pageParam,teacherQuery);
        List<Teacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return  R.ok().data("total",total).data("rows",records);
    }

}
