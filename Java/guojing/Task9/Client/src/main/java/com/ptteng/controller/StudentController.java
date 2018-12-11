package com.ptteng.controller;

import com.ptteng.entity.Student;
import com.ptteng.server.RandomServer;
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
    private RandomServer randomServer;

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public ModelAndView findStudent() {
        logger.info("哈哈哈，我来首页了！");
        ModelAndView modelAndView = new ModelAndView();

//        获取所有的优秀学员信息（SQL写的条件为查询类型是优秀学员）
        List<Student> studentList = randomServer.getStudentService().findStudent();
        logger.info(studentList);

        Long totalStudent = randomServer.getStudentService().countStudent(null);
        logger.info(totalStudent);

        modelAndView.addObject("data", studentList);
        modelAndView.addObject("item", "body1");
        modelAndView.setViewName("myView");
        modelAndView.addObject("totalStudent", totalStudent);
        return modelAndView;
    }


    /*
     *这以后是任务七的东西 */


    //   注册接口，判断相关在service中
    @RequestMapping(value = "/student/register", method = RequestMethod.POST)
    public String register(HttpServletRequest request, Student student, String code, Model model) {
        logger.info("注册入参打印" + student);
        if (student.getName() == null || student.getPassword() == null ||
                student.getTel() == null) {
            return "false";
        }
//        判断手机验证码是否合法
        String ranCode = (String) request.getSession().getAttribute("ranCode");
        logger.info("从session中取出验证码" + ranCode);
        if (!code.equals(ranCode)) {
            model.addAttribute("code", -1);
            return "json";
        }
//判断验证码时间是否超时
        long time = (long) request.getSession().getAttribute("time");
        logger.info("验证码生成时间为" + time);
        long nowTime = System.currentTimeMillis();
        if (nowTime - time > 1000 * 60 * 2) {
            model.addAttribute("code", -1);
            return "json";
        }

        student.setPassword(MD5Util.generate(student.getPassword()));
        Long id = randomServer.getStudentService().register(student);
        logger.info("注册返回主键id" + id);
        model.addAttribute("code", 0);
        return "json";
    }




}
