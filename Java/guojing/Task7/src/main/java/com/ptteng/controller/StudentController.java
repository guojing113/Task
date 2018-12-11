package com.ptteng.controller;

import com.ptteng.entity.Student;
import com.ptteng.service.StudentService;
import com.ptteng.util.encryption.MD5Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class StudentController {

    static Logger logger = Logger.getLogger(StudentController.class);
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public ModelAndView findStudent() {
        logger.info("哈哈哈，我来首页了！");
        ModelAndView modelAndView = new ModelAndView();

//        获取所有的优秀学员信息（SQL写的条件为查询类型是优秀学员）
        List<Student> studentList = studentService.findStudent();
        logger.info(studentList);

        Long totalStudent = studentService.countStudent(null);
        logger.info(totalStudent);

        modelAndView.addObject("data", studentList);
        modelAndView.addObject("item", "body1");
        modelAndView.setViewName("myView");
        modelAndView.addObject("totalStudent", totalStudent);
        return modelAndView;
    }


    @RequestMapping(value = "/student/register", method = RequestMethod.POST)
    public String register(HttpServletRequest request, Student student, Model model) {
        logger.info("注册入参打印" + student);
        if (student.getName() == null || student.getPassword() == null ||
                student.getTel() == null) {
            model.addAttribute("code", -3);
            return "false";
        }
//        这里是取出入参的手机号，然后查出数据库中存的验证码
        Student student1 = new Student();
        student1.setTel(student.getTel());
        Student student2 = studentService.check(student1);
        if (!student.getCode().equals(student2.getCode())) {
            model.addAttribute("code", -301);
            return "json";
        }
//      判断验证码时间是否超时
        long time = student2.getCreateAt();
        logger.info("验证码生成时间为" + time);
        long nowTime = System.currentTimeMillis();
        if (nowTime - time > 1000 * 60 * 3) {
            model.addAttribute("code", -302);
            return "json";
        }
        student.setPassword(MD5Util.generate(student.getPassword()));
//        这个地方有些问题，不应该是插入数据
        Boolean flag= studentService.updateStudent(student);
        logger.info("修改数据库结果==" + flag);
        model.addAttribute("code", 0);
        return "json";
    }


}
