package com.wrx.service;

import com.wrx.pojo.ClassQueryParam;
import com.wrx.pojo.PageResult;
import com.wrx.pojo.Class;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ClassService {
    /**
     * 分页查询所有班级
     */
    PageResult<Class> page(ClassQueryParam param);

    /**
     * 删除班级前检查班级下面是否有学生
     */
    Integer checkStudentByClassId(Integer id);

    /**
     * 根据班级id删除班级信息
     * @param id 班级id
     */
    void deleteClassById(Integer id);

    /**
     * 新增班级
     */
    void addClass(Class clazz);

    /**
     * 根据id查询班级信息回显数据
     */
    Class getById(Integer id);

    /**
     * 修改班级信息
     */
    void updateClassById(Class clazz);

    /**
     * 查询所有班级信息
     */
    List<Class> getAllClass();
}
