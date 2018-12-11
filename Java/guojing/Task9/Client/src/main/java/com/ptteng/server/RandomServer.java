package com.ptteng.server;

import com.ptteng.service.CompanyService;
import com.ptteng.service.ProfessionService;
import com.ptteng.service.StudentService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class RandomServer {

    static Logger logger = Logger.getLogger(RandomServer.class);
    //    这个定义为什么不能放在方法里面呢，感觉应该是可以的？
//        放到方法里需要初始化（需要想一想）
    StudentService studentService;
    CompanyService companyService;
    ProfessionService professionService;
    Map map = new HashMap();
    String name;

    private Map getMap() {
        int a = new Random().nextInt(2);
        logger.info("产生的随机数是" + a);
        String name1 = "rmi://101.132.189.148:11133";
        String name2 = "rmi://101.132.189.148:11134";
        if (a == 1) {
            map.put("name1", name1);
            map.put("name2", name2);
        } else {
            map.put("name1", name2);
            map.put("name2", name1);
        }
        return map;
    }

    public StudentService getStudentService() {
        map = this.getMap();
        try {
            name = map.get("name1") + "/studentService";
            studentService = (StudentService) Naming.lookup(name);
            logger.info("使用的服务地址为" + name);
        } catch (RemoteException | NotBoundException | MalformedURLException e1) {
            e1.printStackTrace();  //这句话意思是在命令行打印异常信息在程序中出错的位置和原因
            logger.info("打印异常"+e1.getMessage()+"打印结束");  //将异常信息打印在日志文件中
            try {
                name = map.get("name2") + "/studentService";
                studentService = (StudentService) Naming.lookup(name);
                logger.info("使用的服务地址为" + name);
            } catch (RemoteException | NotBoundException | MalformedURLException e2) {
                e2.printStackTrace();
            }
        }
        return studentService;
    }


    public CompanyService getCompanyService() {
        map = this.getMap();
        try {
            name = map.get("name1") + "/companyService";
            companyService = (CompanyService) Naming.lookup(name);
            logger.info("使用的服务地址为" + name);
        } catch (RemoteException | NotBoundException | MalformedURLException e1) {
            try {
                name = map.get("name2") + "/companyService";
                companyService = (CompanyService) Naming.lookup(name);
                logger.info("使用的服务地址为" + name);
            } catch (RemoteException | NotBoundException | MalformedURLException e2) {
                e2.printStackTrace();
            }
        }
        return companyService;
    }


    public ProfessionService getProfessionService() {
        map = this.getMap();
        try {
            name = map.get("name1") + "/professionService";
            professionService = (ProfessionService) Naming.lookup(name);
            logger.info("使用的服务地址为" + name);
        } catch (RemoteException | NotBoundException | MalformedURLException e1) {
            try {
                name = map.get("name2") + "/professionService";
                professionService = (ProfessionService) Naming.lookup(name);
                logger.info("使用的服务地址为" + name);
            } catch (RemoteException | NotBoundException | MalformedURLException e2) {
                e2.printStackTrace();
            }
        }
        return professionService;
    }


}
