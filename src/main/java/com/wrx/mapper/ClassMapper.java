package com.wrx.mapper;

import com.wrx.pojo.Class;
import com.wrx.pojo.ClassQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClassMapper {

    /**
     * 数据库按条件查询所有班级信息
     */
    public List<Class> list(ClassQueryParam classQueryParam);

    /**
     * 删除班级前检查该班级下面是否有学生
     * @param id 班级id
     * @return 学生人数
     */
    @Select("SELECT COUNT(1) FROM student s LEFT JOIN class c ON s.clazz_id = c.id WHERE c.id = #{id}")
    Integer checkStudentByClassId(Integer id);

    /**
     * 根据班级id删除班级信息
     */
    void deleteClassById(Integer id);

    /**
     * 新增班级信息
     */
    void addClass(Class clazz);

    /**
     * 根据id查询班级信息做数据回显
     */
    @Select("SELECT * FROM class WHERE id = #{id}")
    Class getById(Integer id);

    /**
     * 修改班级信息，将修改后的数据保存至数据库
     */
    void updateClassById(Class clazz);

    /**
     * 查询所有班级信息
     */
    @Select("SELECT * FROM class")
    List<Class> getAllClass();
}
