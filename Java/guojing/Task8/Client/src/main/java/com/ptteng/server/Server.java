package com.ptteng.server;

import com.ptteng.service.CompanyService;
import com.ptteng.service.ProfessionService;
import com.ptteng.service.StudentService;

public interface Server {
    CompanyService getCompanyService();
    ProfessionService getProfessionService();
    StudentService getStudentService();
}
