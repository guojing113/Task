package com.ptteng.util;


import com.ptteng.service.ProfessionService;
import org.apache.log4j.Logger;
import org.apache.tuscany.sca.node.Node;
import org.apache.tuscany.sca.node.NodeFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Listener implements ServletContextListener {

    static Logger logger = Logger.getLogger(Listener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        logger.info("开始了");
        System.setProperty("java.rmi.server.hostname", "101.132.189.148");
        Node node = NodeFactory.newInstance().createNode("Tuscany.composite");
        node.start();
        try {
            ProfessionService professionService = (ProfessionService) Naming.lookup("rmi://101.132.189.148:11133/professionService");
//            logger.info("测试是否能够获取数据"+professionService.findProfession());
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
        }
        logger.info("service启动");

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
