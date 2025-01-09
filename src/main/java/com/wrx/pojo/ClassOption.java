package com.wrx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 班级学生数量统计封装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassOption {

    private List clazzList;
    private List dataList;

}
