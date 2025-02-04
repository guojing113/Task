package com.jnshu.controller;

import com.jnshu.model.ExcellentStudent;
import com.jnshu.model.JobInfo;
import com.jnshu.model.StudentNum;
import com.jnshu.model.User;
import com.jnshu.service.ESService;
import com.jnshu.service.JIService;
import com.jnshu.service.SNService;
import com.jnshu.service.UserService;
import com.jnshu.tools.DESUtil;
import com.jnshu.tools.MD5Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private ESService eSService;

    @Autowired
    private JIService jIService;

    @Autowired
    private SNService sNService;

    @Autowired
     UserService userService;
    private Logger logger = Logger.getLogger(UserController.class);



 /*   //   跳转到登录
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String toLogin() {
        return "/index";
    }
*/

    public static void LongTime(HttpServletRequest request) {
        long timeStamp = System.currentTimeMillis();
        System.out.println(timeStamp);
        request.setAttribute("time", timeStamp);
    }

    /**
     * 登录跳转
     *
     * @return
     */

    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
    public String toLogin(HttpServletRequest request) {
        LongTime(request);
        return "login";
    }

    /**
     * 登录跳转
     *
     * @return
     */

    @RequestMapping(value = "/toRegister", method = RequestMethod.GET)
    public String toRegister(HttpServletRequest request) {
        LongTime(request);
        return "register";
    }

    /**
     * 注册
     *
     * @param user
     * @param request
     * @return
     */
    @SuppressWarnings("finally")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String add(User user, HttpServletRequest request) {
        try {
            LongTime(request);
            String password = user.getPassword();
            String salt = MD5Util.salt();
            String psSalt = MD5Util.generate(password, salt);
            System.out.println("加盐前：" + password);
            System.out.println("盐值：" + salt);
            System.out.println("加盐后：" + psSalt);
            user.setSalt(salt);
            user.setPassword(psSalt);
            System.out.println(user.getUserName() + "\t" + user.getPassword());
            String str = userService.addInfo(user);
            System.out.println(str);
            request.setAttribute("InfoMessage", str);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("InfoMessage", "添加信息失败！具体异常信息：" + e.getMessage());
        } finally {
            return "result";
        }
    }


    /**
     * 根据id查找
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        try {
            LongTime(request);
            System.out.println("首页");
            List<ExcellentStudent> student = eSService.listAll();
            StudentNum num = sNService.findById(1);
            System.out.println(num.getCumulativeNum());
            request.setAttribute("student", student);
            request.setAttribute("num", num);
            logger.info("获取数值成功");
            return "index";
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("信息载入失败！具体异常信息：" + e.getMessage());
            request.setAttribute("InfoMessage", "信息载入失败！具体异常信息：" + e.getMessage());
            return "result";
        }
    }


    /**
     * 根据id查找
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/u/vocation", method = RequestMethod.GET)
    public String vocation(HttpServletRequest request) {
        try {
            LongTime(request);
            System.out.println("职业");
            List<JobInfo> info = jIService.listAll();
            request.setAttribute("info", info);
            logger.info("获取数值成功");
            return "vocation";
        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();
            logger.error("信息载入失败！具体异常信息：" + e.getMessage());
            request.setAttribute("InfoMessage", "信息载入失败！具体异常信息：" + e.getMessage());
            return "result";
        }
    }


    /**
     * 登录
     *
     * @param user
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, HttpServletRequest request, HttpServletResponse response) {
        try {
            String timeStamp = String.valueOf(System.currentTimeMillis());
            String date = user.getUserName()+","+timeStamp;
            DESUtil desUtil = new DESUtil();
            String cookies = desUtil.encrypt(date);
            System.out.println("cookies:" + cookies);
            LongTime(request);
            System.out.println("------login--qian----" + user.getUserName() + "," + user.getPassword() + ".");

            String password = user.getPassword();
            String salt = userService.selectByName(user.getUserName()).getSalt();
            String psSalt = MD5Util.generate(password, salt);
            boolean loginUser = psSalt.equals(userService.selectByName(user.getUserName()).getPassword());
            if (loginUser = true) {
                addCookie(response, "cookie", cookies, 3600);//插入cookie
                request.setAttribute("loginUser", user.getUserName());
                return "index";
            } else {
                request.setAttribute("loginUser", "登录失败");
                return "result";
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("InfoMessage", "登录失败！具体异常信息：" + e.getMessage());
            return "result";
        }
    }

    /**
     * 设置cookie
     *
     * @param response
     * @param name     cookie名字
     * @param value    cookie值
     * @param maxAge   cookie生命周期  以秒为单位
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxAge > 0) cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
        System.out.println("创建cookies");
    }

    @RequestMapping("/page/login")
    public String showLogin(String redirectURL,Model model) {
        //上一个页面的url传递给登录页面，当登录页面成功登录后，由登录页面跳转到redirectURL指向的页面（js完成）
        model.addAttribute("redirect",redirectURL);
        return "login";
    }

    /**
     * 删除cookie
     * @param request
     * @param response
     */
    @RequestMapping("/delCookie")
    public String delCookie(HttpServletRequest request,HttpServletResponse response){
        LongTime(request);
        Cookie[] cookies = request.getCookies();
        if (null==cookies) {
            System.out.println("没有cookie==============");
        } else {
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("cookie")){
                    cookie.setValue(null);
                    cookie.setMaxAge(0);// 立即销毁cookie
                    cookie.setPath("/");
                    System.out.println("被删除的cookie名字为:"+cookie.getName());
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        return "index";
    }
}
