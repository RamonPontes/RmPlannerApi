package com.ramon.pontes.RmPlannerApi.repository;

import com.ramon.pontes.RmPlannerApi.model.Company.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    List<Company> findAllByOwner(int owner);
}
