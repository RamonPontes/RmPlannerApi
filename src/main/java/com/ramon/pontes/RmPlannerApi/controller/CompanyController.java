package com.ramon.pontes.RmPlannerApi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/company")
public class CompanyController {
    @PostMapping
    public ResponseEntity<?> CreateCompany() {
        return ResponseEntity.ok().build();
    }
}
