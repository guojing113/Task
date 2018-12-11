package com.ptteng.server;

import com.ptteng.service.CompanyService;
import com.ptteng.service.ProfessionService;
import com.ptteng.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class RandomServer1 {

    @Autowired
    @Qualifier("studentService")
    private StudentService studentService;

    @Autowired
    @Qualifier("studentService1")
    private StudentService studentService1;

    @Autowired
    @Qualifier("companyService")
    private CompanyService companyService;

    @Autowired
    @Qualifier("companyService1")
    private CompanyService companyService1;

    @Autowired
    @Qualifier("professionService")
    private ProfessionService professionService;

    @Autowired
    @Qualifier("professionService1")
    private ProfessionService professionService1;


    public StudentService getStudentService() {
        int a = new Random().nextInt(2);
        Map map = new HashMap();
        if (a == 1) {
            map.put("server", studentService);
            System.out.println("使用的第一方案");
            map.put("server1", studentService1);
        } else {
            map.put("server", studentService1);
            map.put("server1", studentService);
        }
        System.out.println("产生的随机数" + a);
        try {
            System.out.println("测试测试" + map.get("server"));
            return (StudentService) map.get("server");
        } catch (Exception e) {
            System.out.println("测试测试" + map.get("server1"));
            return (StudentService) map.get("server");
        }
    }


    public CompanyService getCompanyService() {
        int a = new Random().nextInt(2);
        Map map = new HashMap();
        if (a == 1) {
            map.put("server", companyService1);
            System.out.println("使用的第一方案");
            map.put("server1", companyService);
        } else {
            map.put("server", companyService);
            System.out.println("使用的第二方案");
            map.put("server1", companyService1);
        }
        System.out.println("产生的随机数" + a);
        try {
            System.out.println("测试测试" + map.get("server"));
            return (CompanyService) map.get("server");
        } catch (Exception e) {
            System.out.println("测试测试" + map.get("server1"));
            return (CompanyService) map.get("server");
        }
    }


    public ProfessionService getProfessionService() {
        int a = new Random().nextInt(2);
        Map map = new HashMap();
        if (a == 1) {
            map.put("server", professionService);

            map.put("server1", professionService1);
        } else {
            map.put("server", professionService1);
            map.put("server1", professionService);
        }
        System.out.println("产生的随机数" + a);
        try {
            System.out.println("测试测试" + map.get("server"));
            return (ProfessionService) map.get("server");
        } catch (Exception e) {
            System.out.println("测试测试" + map.get("server"));
            return (ProfessionService) map.get("server");
        }
    }


}
