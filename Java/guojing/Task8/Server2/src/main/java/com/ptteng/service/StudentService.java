package com.ptteng.service;


import com.ptteng.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> findStudent();

    Long countStudent(Integer classifyId);

    Long register(Student student);

    Student login(String name);

    Boolean updateStudent(Student student);
}
