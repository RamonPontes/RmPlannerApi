package com.ramon.pontes.RmPlannerApi.service;

import com.ramon.pontes.RmPlannerApi.exception.NotFoundException;
import com.ramon.pontes.RmPlannerApi.model.Company.Company;
import com.ramon.pontes.RmPlannerApi.model.Schedule.Schedule;
import com.ramon.pontes.RmPlannerApi.repository.CompanyRepository;
import com.ramon.pontes.RmPlannerApi.repository.ScheduleRepository;
import com.ramon.pontes.RmPlannerApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    UserRepository userRepository;

    public void registerSchedule(Schedule data) {
        companyRepository.findById(data.getCompany_id())
                .orElseThrow(() -> new NotFoundException("Company with ID " + data.getCompany_id() + " not found"));

        scheduleRepository.save(data);
    }
}