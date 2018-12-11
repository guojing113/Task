package com.ptteng.controller;

import com.ptteng.entity.Student;
import com.ptteng.service.StudentService;
import com.ptteng.util.API.NoteVerifyUtil;
import com.ptteng.util.API.SendCloudUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class VerificationController {


    static Logger logger = Logger.getLogger(VerificationController.class);
    @Autowired
    private NoteVerifyUtil noteVerifyUtil;
    @Autowired
    private SendCloudUtil sendCloudUtil;
    @Autowired
    private StudentService studentService;


    //    这样将code写入数据库还是有些问题，如果发完验证码但是没有注册，这样以后就无法注册了？
    @RequestMapping(value = "/getTelCode", method = RequestMethod.GET)
    public String getTelCode(HttpServletRequest request, String tel, Model model) throws IOException {
        logger.info("获取验证码入参" + tel);
        Student student = new Student();
//  将String类型的电话号码转化为Long型
        student.setTel(tel);
        if (studentService.check(student) != null) {
            model.addAttribute("code", -2);
            return "json";
        }
        try {
            //      调用第三方API发送验证码
            String ranCode = noteVerifyUtil.NoteVerify(tel);
            logger.info("获取的验证码" + ranCode);
            student.setCode(ranCode);
            student.setCreateAt(System.currentTimeMillis());
            logger.info("测试数据" + student);
//           将手机号和验证码存入数据库中(这里需要设置一个状态，要不然验证码写入
//           数据库后如果没有注册完成，后面就无法再进行注册)
            long id = studentService.register(student);
            logger.info("返回的主键id为" + id);
            model.addAttribute("code", 0);
        } catch (Exception e) {
//            这里需要考虑一下异常的种类，才好判断
            model.addAttribute("code", -1);
        }
        return "json";
    }


    //    这个先不删，考虑一下session那里的问题，统计在线登录人数
    @RequestMapping(value = "/getMailCode", method = RequestMethod.GET)
    public String getMailCode(HttpServletRequest request, String mail, Model model) throws IOException {
        logger.info("获取验证码入参" + mail);
//        验证邮箱是否已经注册
        Student student = new Student();
        student.setMail(mail);
        if (studentService.check(student) != null) {
            model.addAttribute("code", -2);
            return "json";
        }
        try {
//       调用第三方API发送验证码
            String ranCode = sendCloudUtil.SendCloud(mail);
            logger.info("获取的验证码" + ranCode);

//      这里的session一创建就会被监听器监听到，我需要不让它被监听到(这里可以考虑换一下思路)。
//        将验证码存放起来，便于注册时验证是否合法
            request.getSession().setAttribute("ranCode", ranCode);
//        储存生成验证码的时间，在注册时验证时效性
            request.getSession().setAttribute("time", System.currentTimeMillis());
            logger.info("验证码生成时间" + System.currentTimeMillis());
            model.addAttribute("code", 0);
        } catch (Exception e) {
            model.addAttribute("code", -1);
        }

        return "json";
    }


}
