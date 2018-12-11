package com.ptteng.util.Intercepter;


import com.ptteng.util.encryption.DESUtil;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//通过实现HandleInterceptor接口，重写里面的方法；
//重写preHandle方法，返回的是true，继续下面的方法；如果返回的是false，请求结束
//登录验证就是验证cookie中是否有登录信息，有就表示已经登录了，其他的不用考虑
public class LoginInterceptor implements HandlerInterceptor {

    static Logger logger = Logger.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
//       获取请求中的cookie信息
        Cookie[] cookies = request.getCookies();
        if (cookies.length == 0) {
            System.out.println("没有cookie，无法登陆呀！");
        } else {
            System.out.println("你有cookie呀，我看看你行不行");
            String name = "";
            String token = "";
            DESUtil desUtil = new DESUtil();
//       遍历cookie如果找到登录状态则返回true执行原来controller的方法
            for (Cookie cookie : cookies) {
                System.out.println("cookie.getValue()===" + cookie.getValue());

                if (cookie.getName().equals("name")) {
                    name = cookie.getValue();
                    logger.info("登录用户name-------------" + name);
                }

                if (cookie.getName().equals("token")) {
                    logger.info("token验证" + cookie.getValue());
                    //token解密
                    token = cookie.getValue();
                    String str3 = desUtil.decrypt(token);
                    logger.info("解密后的token是" + str3);
                    //取出用户name信息
                    String name2 = str3.split("\\|")[1];
                    logger.info("cookie中取出的解密后名字是" + name2);
                    //根据token解密后，验证用户name是否一致
                    System.out.println(name.equals(name2));
                    System.out.println("name是" + name + "---------name2是" + name2);
                    if (name.equals(name2)) {
                        System.out.println("已经登录啦");
                        return true;
                    }

                }
            }
        }
//        没有找到登录状态则重定向到登录页，返回false，不执行原来controller的方法
        response.sendRedirect("/student");
        return false;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}
