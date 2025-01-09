package com.wrx.controller;

import com.wrx.anno.Log;
import com.wrx.exception.ClassHasStudentsException;
import com.wrx.pojo.ClassQueryParam;
import com.wrx.pojo.PageResult;
import com.wrx.pojo.Result;
import com.wrx.service.ClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wrx.pojo.Class;

import java.util.List;

/**
 * 班级管理
 */
@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClassController {

    @Autowired
    private ClassService classService;



    /**
     * 班级条件分页查询
     */
    @GetMapping
    public Result getAllClass(ClassQueryParam param) {
        log.info("分页查询请求数据：{}", param);
        PageResult pageResult = classService.page(param);
        return Result.success(pageResult);
    }

    /**
     * 根据id删除班级
     * PathVariable注解将路径后面{id}形式的参数，绑定到方法声明的形参中
     */
    @Log
    @DeleteMapping("{id}")
    public Result deleteClassById( @PathVariable Integer id) {
        log.info("删除班级id为：{}",id);
        // 检查班级下是否有学生
        Integer num = classService.checkStudentByClassId(id);
        if (num > 0) {
            throw new ClassHasStudentsException();
        }
        classService.deleteClassById(id);
        return Result.success();
    }

    /**
     * 新增班级信息
     * RequestBody注解将post请求体中的json数据封装到方法声明的对象中
     */
    @Log
    @PostMapping
    public Result addClass(@RequestBody Class clazz) {
        log.info("新增班级信息：{}",clazz);
        classService.addClass(clazz);
        return Result.success();
    }

    /**
     * 修改班级信息-根据id查询班级信息数据回显
     */
    @GetMapping("{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据id回显班级信息，id为：{}",id);
        Class clazz = classService.getById(id);
        return Result.success(clazz);
    }

    /**
     * 修改班级信息-将修改信息保存至数据库
     */
    @Log
    @PutMapping
    public Result updateClassById(@RequestBody Class clazz) {
        log.info("修改班级信息为：{}",clazz);
        classService.updateClassById(clazz);
        return Result.success();
    }

    /**
     * 查询所有班级信息
     */
    @GetMapping("/list")
    public Result getAllClass() {
        log.info("获取所有班级信息！");
        List<Class> allClass = classService.getAllClass();
        return Result.success(allClass);
    }

}
