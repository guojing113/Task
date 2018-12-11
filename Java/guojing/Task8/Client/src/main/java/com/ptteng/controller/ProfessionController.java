package com.ptteng.controller;

import com.ptteng.entity.Profession;
import com.ptteng.server.RandomServer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
@Controller
public class ProfessionController {

    static Logger logger=Logger.getLogger(ProfessionController.class);
    @Autowired
    private RandomServer randomServer;

    @RequestMapping(value = "/u/profession",method = RequestMethod.GET)
    public ModelAndView findProfession(){
        logger.info("哈哈哈，我来职业了！");
        ModelAndView modelAndView=new ModelAndView();
//        获取所有的方向分类列表信息
        List<Profession> professions=randomServer.getProfessionService().findProfession();
        List<Profession> list1=new ArrayList<>();
        List<Profession> list2=new ArrayList<>();

//        循环遍历所有的方向分类列表信息，方向为前端的放在一个list集合里面……
        for(Profession p:professions){
            switch (p.getDirection()){
                case "前端开发方向":
                    list1.add(p);
                    break;
                case "后端开发方向":
                    list2.add(p);
                    break;
            }
        }
        logger.info(list1);
        logger.info(list2);
        modelAndView.addObject("item","body3");
        modelAndView.addObject("data1",list1);
        modelAndView.addObject("data2",list2);
        modelAndView.setViewName("myView");
        return modelAndView;
    }



}
