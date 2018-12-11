package com.ptteng.server;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Server {

    static Logger logger = Logger.getLogger(Server.class);

    public static void main(String[] args) {

        System.setProperty("java.rmi.server.hostname", "101.132.189.148");
        new ClassPathXmlApplicationContext("ApplicationContext.xml");
        logger.info("启动服务");
    }


}
