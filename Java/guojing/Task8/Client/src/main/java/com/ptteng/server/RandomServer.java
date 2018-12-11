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
public class RandomServer {

    @Autowired
    @Qualifier("ServerImpl")
    private Server server;
    @Autowired
    @Qualifier("ServerImpl1")
    private Server server1;


    private Map getMap() {
        Map map = new HashMap();
        int a = new Random().nextInt(2);
        System.out.println("产生的随机数为==" + a);
        if (a == 1) {
            map.put("server", server);
            System.out.println("使用第一种方案");
            map.put("server1", server1);
        } else {
            map.put("server", server1);
            System.out.println("使用第二种方案");
            map.put("server1", server);
        }
        return map;
    }


    public StudentService getStudentService() {
        System.out.println("进入方法中");
        Map map = this.getMap();
        Server server3;
        StudentService studentService;
        try {
//            这里注意return里面的东西与try无关，不能将需要try的代码放在return中
            server3 = (Server) map.get("server");
            System.out.println("测试使用的server" + server3);
            studentService = server3.getStudentService();
            System.out.println("测试返回的services" + studentService);

        } catch (Exception e) {
            server3 = (Server) map.get("server1");
            studentService = server3.getStudentService();

        }
        return studentService;
    }


    public ProfessionService getProfessionService() {
        System.out.println("进入方法中");
        Map map = this.getMap();
        Server server3;
        ProfessionService professionService;
        try {
            server3 = (Server) map.get("server");
            System.out.println("测试使用的server" + server3);
            professionService = server3.getProfessionService();
            System.out.println("测试返回的services" + professionService);

        } catch (Exception e) {
            server3 = (Server) map.get("server1");
            System.out.println("使用的是我么？");
            professionService = server3.getProfessionService();
            System.out.println("对，就是我呀，我是catch里面的");
        }
        return professionService;
    }

    //这里有问题，try-catch有问题
    public CompanyService getCompanyService() {
        System.out.println("进入方法中");
        Map map = this.getMap();
        Server server3;
        CompanyService companyService;
        try {
            server3 = (Server) map.get("server");
            System.out.println("测试使用的server是" + server3);
            companyService = server3.getCompanyService();
            System.out.println("返回的方法是=="+companyService);

        } catch (Exception e) {
            server3 = (Server) map.get("server1");
            System.out.println("使用的是我么？"+server3);
            companyService = server3.getCompanyService();
            System.out.println("对，就是我呀，我是catch里面的==="+companyService);
        }
        return companyService;
    }


}
