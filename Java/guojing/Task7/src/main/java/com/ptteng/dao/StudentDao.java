package com.ptteng.dao;


import com.ptteng.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;


//因为mybatis设置那离扫描的是dao包名，整个包与所有映射文件对应起来生成工厂bean(还需要看一看)
public interface StudentDao {
    List<Student> findStudent();

    Long countStudent(Integer classifyId);

    Long register(Student student);

    Student login(String tel);

    Boolean updateStudent(Student student);

    Student check(Student student);
}
