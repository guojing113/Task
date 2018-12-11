package com.ptteng.server;

import com.ptteng.service.CompanyService;
import com.ptteng.service.ProfessionService;
import com.ptteng.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("ServerImpl1")
public class ServerImpl1 implements Server{

    @Autowired
    @Qualifier("studentService1")
    private StudentService studentService1;

    @Autowired
    @Qualifier("companyService1")
    private CompanyService companyService1;

    @Autowired
    @Qualifier("professionService1")
    private ProfessionService professionService1;



    @Override
    public CompanyService getCompanyService() {
        return companyService1;
    }

    @Override
    public ProfessionService getProfessionService() {
        return professionService1;
    }

    @Override
    public StudentService getStudentService() {
        return studentService1;
    }
}
