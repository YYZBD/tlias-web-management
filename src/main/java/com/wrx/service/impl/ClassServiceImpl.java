package com.wrx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wrx.mapper.ClassMapper;
import com.wrx.pojo.Class;
import com.wrx.pojo.ClassQueryParam;
import com.wrx.pojo.PageResult;
import com.wrx.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassMapper classMapper;

    /**
     * 班级条件分页查询
     */
    @Override
    public PageResult page(ClassQueryParam classQueryParam) {
        // 1、设置分页参数
        PageHelper.startPage(classQueryParam.getPage(),classQueryParam.getPageSize());
        // 2、调用mapper接口方法，获取班级信息列表
        List<Class> classList = classMapper.list(classQueryParam);
        // 3、需要返回前端总数和列表内容，因此强转为pagehelper里提供的PageInfo封装类，里面可以直接获取前端需要的内容
        PageInfo<Class> pageInfo = new PageInfo<>(classList);
        //                    或取总记录数，和班级信息列表，封装到PageResult中
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public Integer checkStudentByClassId(Integer id) {
        Integer num = classMapper.checkStudentByClassId(id);
        return num;
    }

    @Override
    public void deleteClassById(Integer id) {
        classMapper.deleteClassById(id);
    }

    @Override
    public void addClass(Class clazz) {
        // 1、为创建时间赋值
        clazz.setCreateTime(LocalDateTime.now());
        // 2、为修改时间字段赋值
        clazz.setUpdateTime(LocalDateTime.now());
        classMapper.addClass(clazz);
    }

    @Override
    public Class getById(Integer id) {
        Class clazz = classMapper.getById(id);
        return clazz;
    }

    @Override
    public void updateClassById(Class clazz) {
        // 更新数据库操作时间
        clazz.setUpdateTime(LocalDateTime.now());
        classMapper.updateClassById(clazz);
    }

    public List<Class> getAllClass() {
        List<Class> allClass = classMapper.getAllClass();
        return allClass;
    }

}
