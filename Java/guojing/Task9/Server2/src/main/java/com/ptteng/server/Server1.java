package com.ptteng.server;//package com.ptteng.server;
//
//import com.ptteng.service.ProfessionService;
//import org.apache.log4j.Logger;
//import org.apache.tuscany.sca.node.Node;
//import org.apache.tuscany.sca.node.NodeFactory;
//
//import java.net.MalformedURLException;
//import java.rmi.Naming;
//import java.rmi.NotBoundException;
//import java.rmi.RemoteException;
//
//
//public class Server1 {
//
//    static Logger logger = Logger.getLogger(Server1.class);
//
//    public static void main(String[] args) {
//        System.out.println("开始了");
//        Node node = NodeFactory.newInstance().createNode("Tuscany.composite");
//        node.start();
//        try {
//            ProfessionService professionService = (ProfessionService) Naming.lookup("rmi://101.132.189.148:11133/professionService");
//            logger.info("测试是否能够获取数据"+professionService.findProfession());
//        } catch (NotBoundException | MalformedURLException | RemoteException e) {
//        }
//        logger.info("service启动");
//    }
//
//
//}
