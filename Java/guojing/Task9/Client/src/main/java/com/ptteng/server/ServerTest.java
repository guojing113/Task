package com.ptteng.server;

import com.ptteng.entity.Company;
import com.ptteng.entity.Profession;
import com.ptteng.service.CompanyService;
import com.ptteng.service.ProfessionService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;


public class ServerTest {

    private static CompanyService companyService;

    private static CompanyService gerCompanyService() {
        try {
            companyService= (CompanyService) Naming.lookup("rmi://101.132.189.148:11133/companyService");
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
        return companyService;
    }

    public static void main(String[] args) {

        companyService= ServerTest.gerCompanyService();
        List<Company> companyList=companyService.findCompany();

        System.out.println("测试数据"+companyList);
    }

}
