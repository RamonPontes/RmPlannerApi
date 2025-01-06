package com.ramon.pontes.RmPlannerApi.service;

import com.ramon.pontes.RmPlannerApi.dto.request.company.CreateCompanyRequest;
import com.ramon.pontes.RmPlannerApi.model.Company.Company;
import com.ramon.pontes.RmPlannerApi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    public void CreateCompany(Company data) {
        companyRepository.save(data);
    }
}
