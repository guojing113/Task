package com.ptteng.controller;


import com.ptteng.entity.Company;
import com.ptteng.server.RandomServer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CompanyController {

    static Logger logger=Logger.getLogger(CompanyController.class);
    @Autowired
    private RandomServer randomServer;

    @RequestMapping(value = "/u/company", method = RequestMethod.GET)
    public ModelAndView findCompany() {
        logger.info("哈哈哈，我来企业了！");
        ModelAndView modelAndView = new ModelAndView();
        List<Company> companyList = randomServer.getCompanyService().findCompany();
        logger.info(companyList);
        modelAndView.addObject("data",companyList);
        modelAndView.addObject("item", "body2");
        modelAndView.setViewName("myView");
        return modelAndView;
    }


}
