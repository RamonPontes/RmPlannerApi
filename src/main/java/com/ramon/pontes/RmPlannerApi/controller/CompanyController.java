package com.ramon.pontes.RmPlannerApi.controller;

import com.ramon.pontes.RmPlannerApi.dto.request.company.CreateCompanyRequest;
import com.ramon.pontes.RmPlannerApi.model.Company.Company;
import com.ramon.pontes.RmPlannerApi.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping
    public ResponseEntity<?> CreateCompany(@RequestBody CreateCompanyRequest data) {
        companyService.CreateCompany(new Company(data.name()));
        return ResponseEntity.ok().build();
    }
}
