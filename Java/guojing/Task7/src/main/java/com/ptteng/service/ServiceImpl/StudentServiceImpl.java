package com.ptteng.service.ServiceImpl;

import com.ptteng.dao.StudentDao;
import com.ptteng.entity.Student;
import com.ptteng.service.StudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    static Logger logger = Logger.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentDao studentDao;


    @Override
    public List<Student> findStudent() {
        return studentDao.findStudent();
    }

    @Override
    public Long countStudent(Integer classifyId) {
        return studentDao.countStudent(classifyId);
    }

    @Override
    public Long register(Student student) {
        studentDao.register(student);
        Long id = student.getId();
        logger.info("注册返回主键id" + id);
        return id;
    }

    @Override
    public Student login(String tel) {
        return studentDao.login(tel);
    }

    @Override
    public Boolean updateStudent(Student student) {

        return studentDao.updateStudent(student);
    }

    @Override
    public Student check(Student student) {
        return studentDao.check(student);
    }
}
