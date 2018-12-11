package com.ptteng.server;

import com.ptteng.service.CompanyService;
import com.ptteng.service.ProfessionService;
import com.ptteng.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("ServerImpl")
public class ServerImpl implements Server{

    @Autowired
    @Qualifier("studentService")
    private StudentService studentService;

    @Autowired
    @Qualifier("companyService")
    private CompanyService companyService;

    @Autowired
    @Qualifier("professionService")
    private ProfessionService professionService;


    @Override
    public CompanyService getCompanyService() {
        return companyService;
    }

    @Override
    public ProfessionService getProfessionService() {
        return professionService;
    }

    @Override
    public StudentService getStudentService() {
        return studentService;
    }
}
