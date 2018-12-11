package com.ptteng.listener;

import com.ptteng.entity.Student;
import com.ptteng.server.RandomServer;
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
   private RandomServer randomServer;

    static Logger logger = Logger.getLogger(LoginServlet.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("测试有没有进入LoginServlet");
        Student student = new Student();
        student.setName(request.getParameter("name"));
        student.setPassword(request.getParameter("password"));

        if (student.getName() == null || student.getPassword() == null) {
            logger.info("登录参数判定");
            response.sendRedirect("/login.jsp");
            return;
        }
//      对姓名进行加密
//        user.setName(MD5Util.MD5(user.getName()));
//        logger.info("登录打印姓名"+user.getName());
//        logger.info("登录打印密码"+user.getPassword());

        String password="";
        try {
            String name = student.getName();
            logger.info("登录name==============" + name);
            logger.info("登录studentService==============" + randomServer.getStudentService());
            Student student1= randomServer.getStudentService().login(name);
            password = student1.getPassword();

        } catch (Exception e) {
            response.sendRedirect("/login.jsp");
            return;
        }
//        验证登录输入的密码与数据库保存的密码是否一致
        if (MD5Util.verify(student.getPassword(), password)) {
            logger.info("登录成功");
            Cookie tokenCookie = new Cookie("token", JWT.createJWT(student.getName()));
            tokenCookie.setMaxAge(30 * 60);
            tokenCookie.setPath("/");
            response.addCookie(tokenCookie);
            HttpSession session = request.getSession();
            session.setAttribute("name", student.getName());//将属性保存到session会话中
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
