package com.ptteng.dao;


import com.ptteng.entity.Company;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CompanyDao {

    List<Company> findCompany();
}
