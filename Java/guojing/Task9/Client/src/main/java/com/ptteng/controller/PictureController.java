package com.ptteng.controller;

import com.ptteng.entity.Student;
import com.ptteng.server.RandomServer;
import com.ptteng.util.API.COSClientUtil;
import com.ptteng.util.API.OSSClientUtil;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URL;

@Controller
public class PictureController {

    @Autowired
    private OSSClientUtil ossClientUtil;
    @Autowired
    private COSClientUtil cosClientUtil;
    @Autowired
    private RandomServer randomServer;

    @RequestMapping(value = "/pictureupload", method = RequestMethod.GET)
    public String pictureUpload() {
        return "pictureUpload";
    }

//  阿里云图片上传
    @RequestMapping(value = "/osspicture", method = RequestMethod.POST)
    public ModelAndView updatePicture(HttpServletRequest request, String name,
                                MultipartFile pictureFile, ModelAndView modelAndView) throws Exception {

        //上传OSS
//        key是图片文件的唯一标识
        String key=name+String.valueOf(System.currentTimeMillis())+".jpg";
        System.out.println("测试一下"+key);
        System.out.println("测试入参"+name);
        System.out.println("测试类加载"+ossClientUtil);
        System.out.println("测试上传的图片"+pictureFile.getBytes());
        URL url1 = ossClientUtil.uploadInputStream(key, pictureFile.getBytes());
        System.out.println("测试图片地址"+url1);
        Student student=new Student();
        student.setName(name);
        student.setPhoto(String.valueOf(url1));
        randomServer.getStudentService().updateStudent(student);
        //重定向到查询所有用户的Controller，测试图片回显
        modelAndView.setViewName("redirect:student");
        return modelAndView;
    }


//    腾讯云图片上传
    @RequestMapping(value = "/cospicture",method = RequestMethod.POST)
    public ModelAndView Picture(HttpServletRequest request, String name,
                                MultipartFile pictureFile, ModelAndView modelAndView) throws Exception {

//        multiPartFile转化为file，这种方法会在项目的根目录下生成一个文件
        CommonsMultipartFile cf= (CommonsMultipartFile)pictureFile;
        DiskFileItem fi = (DiskFileItem)cf.getFileItem();
        File file = fi.getStoreLocation();

        //上传OSS
//        key是图片文件的唯一标识
        String key=name+String.valueOf(System.currentTimeMillis())+".jpg";
        System.out.println("测试一下"+key);
        System.out.println("测试入参"+name);
        System.out.println("测试类加载"+ossClientUtil);
        System.out.println("测试上传的图片"+pictureFile);
        URL url1 = cosClientUtil.uploadFile(key, file);
//        URL url1 = cosClientUtil.uploadFile();
        System.out.println("测试图片地址"+url1);
        Student student=new Student();
        student.setName(name);
        student.setPhoto(String.valueOf(url1));
        randomServer.getStudentService().updateStudent(student);
        //重定向到查询所有用户的Controller，测试图片回显
        modelAndView.setViewName("redirect:student");
        return modelAndView;
    }





}
