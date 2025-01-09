package com.wrx.exception;


public class ClassHasStudentsException extends RuntimeException {
    public ClassHasStudentsException() {
        super("对不起, 该班级下有学生, 不能直接删除。");
    }
}
