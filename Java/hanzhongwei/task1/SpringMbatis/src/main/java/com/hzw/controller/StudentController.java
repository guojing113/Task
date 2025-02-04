package com.hzw.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzw.pojo.Student;
import com.hzw.service.StudentService;
import com.hzw.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.logging.Logger;

// 告诉spring mvc这是一个控制器类
@Controller
@RequestMapping("")
public class StudentController {
    @Autowired
    StudentService studentService;

    Logger logger = Logger.getLogger("StudentController.class");

    @RequestMapping("listStudent")
    public ModelAndView listStudent(Page page){
       logger.info("进入controller，开始查询");
       ModelAndView mav = new ModelAndView();
        PageHelper.offsetPage(page.getStart(),5);
       List<Student> cs = studentService.getAll();
       int total = (int) new PageInfo<>(cs).getTotal();
//       List<Student> cs = studentService.getAll(page);
//       int total = studentService.total();
       page.caculateLast(total);
        // 放入转发参数
       mav.addObject("cs",cs);
        // 放入jsp路径
       mav.setViewName("listStudent");
       return mav;
    }

    @RequestMapping("addStudent")
    public ModelAndView addStudent(Student student){
        logger.info("增加"+student);
        studentService.addStu(student);
        ModelAndView mav = new ModelAndView("redirect:/listStudent");
        return mav;
    }

    @RequestMapping("deleteStudent")
    public ModelAndView deleteStudent(Student student){
        logger.info("删除"+student);
        studentService.deleteStu(student.getS_id());
        ModelAndView mav = new ModelAndView("redirect:/listStudent");
        logger.info("删除的mav是："+mav);
        return mav;
    }

    @RequestMapping("updateStudent")
    public ModelAndView updateStudent(Student student){
        logger.info("更新"+student);
        studentService.updateStu(student);
        ModelAndView mav = new ModelAndView("redirect:/listStudent");
        return mav;
    }

    @RequestMapping("getIdStudent")
    public ModelAndView getIdStudent(Student student){
        logger.info("单体查询"+student);
        Student c = studentService.getId(student.getS_id());
        ModelAndView mav = new ModelAndView("updateStudent");
        mav.addObject("c", c);
        return mav;
    }
}
