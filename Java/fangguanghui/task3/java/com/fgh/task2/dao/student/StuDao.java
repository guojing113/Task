package com.fgh.task2.dao.student;

import com.fgh.task2.model.Stu;

import java.util.List;

public interface StuDao {
    Integer allCount();
    Integer findWorking();
    List<Stu>findOutstanding();
}
