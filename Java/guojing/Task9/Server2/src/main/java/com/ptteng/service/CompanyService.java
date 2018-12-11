package com.ptteng.service;

import com.ptteng.entity.Company;
import org.oasisopen.sca.annotation.Remotable;

import java.util.List;

@Remotable
public interface CompanyService {

    List<Company> findCompany();
}
