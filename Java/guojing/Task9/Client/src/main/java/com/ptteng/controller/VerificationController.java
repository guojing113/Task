package com.ptteng.controller;


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


    @RequestMapping(value = "/getNoteCode", method = RequestMethod.GET)
    public String noteVerify(HttpServletRequest request, String tel, Model model) throws IOException {
        logger.info("获取验证码入参" + tel);

//      这里可以使用手机或者是邮箱获取验证码
        String ranCode = noteVerifyUtil.NoteVerify(tel);
//        String ranCode = sendCloudUtil.SendCloud(tel);
        logger.info("获取的验证码" + ranCode);
//        将验证码存放起来，便于注册时验证是否合法
        request.getSession().setAttribute("ranCode", ranCode);
//        储存生成验证码的时间，在注册时验证时效性
        request.getSession().setAttribute("time", System.currentTimeMillis());

        logger.info("验证码生成时间" + System.currentTimeMillis());
        model.addAttribute("code", 0);
        return "json";
    }


}
