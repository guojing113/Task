package com.ptteng.LoginExit;

import com.ptteng.entity.Student;
import com.ptteng.service.StudentService;
import com.ptteng.util.JWT;
import com.ptteng.util.encryption.MD5Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

//初始化然后才可以使用注解获取service
    public void init() throws ServletException {
        super.init();
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        AutowireCapableBeanFactory factory = wac.getAutowireCapableBeanFactory();
        factory.autowireBean(this);
    }

    @Autowired
    private StudentService studentService;

    static Logger logger = Logger.getLogger(LoginServlet.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("测试有没有进入LoginServlet");
        Student student = new Student();
//       将String型的手机号转化为long型
        student.setTel(request.getParameter("tel"));
        student.setPassword(request.getParameter("password"));
        if (student.getTel() == null || student.getPassword() == null) {
            logger.info("登录参数判定");
            response.sendRedirect("/login.jsp");
            return;
        }
        String password="";
        try {
            String tel = student.getTel();
            logger.info("登录name==============" + tel);
            logger.info("登录studentService==============" + studentService);
            Student student1= studentService.login(tel);
            password = student1.getPassword();

        } catch (Exception e) {
            response.sendRedirect("/login.jsp");
            return;
        }
//        验证登录输入的密码与数据库保存的密码是否一致
        if (MD5Util.verify(student.getPassword(), password)) {
            logger.info("登录成功");
            Cookie tokenCookie = new Cookie("token", JWT.createJWT(student.getTel()));
            tokenCookie.setMaxAge(30 * 60);
            tokenCookie.setPath("/");
            response.addCookie(tokenCookie);
            HttpSession session = request.getSession();
            session.setAttribute("tel", student.getTel());//将属性保存到session会话中
            response.sendRedirect("/student");
            return;
        }
        response.sendRedirect("/login.jsp");
        return;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(req, resp);
    }


}
