package com.ramon.pontes.RmPlannerApi.controller;

import com.ramon.pontes.RmPlannerApi.dto.request.company.CreateCompanyRequest;
import com.ramon.pontes.RmPlannerApi.model.Company.Company;
import com.ramon.pontes.RmPlannerApi.model.User.User;
import com.ramon.pontes.RmPlannerApi.repository.UserRepository;
import com.ramon.pontes.RmPlannerApi.service.CompanyService;
import com.ramon.pontes.RmPlannerApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> CreateCompany(@RequestBody CreateCompanyRequest data) {
        User user = userRepository.findUserByEmail(userService.getAuthenticatedUserEmail());

        companyService.CreateCompany(new Company(data.name(), user.getUser_id()));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> GetUserAllCompany() {
        User user = userRepository.findUserByEmail(userService.getAuthenticatedUserEmail());

        List<Company> company = companyService.getAllUserCompany(user.getUser_id());

        return ResponseEntity.ok(company);
    }
}
