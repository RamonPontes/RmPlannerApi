package com.ramon.pontes.RmPlannerApi.controller;

import com.ramon.pontes.RmPlannerApi.dto.request.schedule.RegisterScheduleRequest;
import com.ramon.pontes.RmPlannerApi.exception.NotFoundException;
import com.ramon.pontes.RmPlannerApi.model.Company.Company;
import com.ramon.pontes.RmPlannerApi.model.Schedule.Schedule;
import com.ramon.pontes.RmPlannerApi.model.User.User;
import com.ramon.pontes.RmPlannerApi.repository.CompanyRepository;
import com.ramon.pontes.RmPlannerApi.repository.ScheduleRepository;
import com.ramon.pontes.RmPlannerApi.repository.UserRepository;
import com.ramon.pontes.RmPlannerApi.service.ScheduleService;
import com.ramon.pontes.RmPlannerApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    CompanyRepository companyRepository;

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(NotFoundException ex) {
        return ex.getMessage();
    }

    @PostMapping
    public ResponseEntity<?> registerSchedule(@RequestBody RegisterScheduleRequest data) {
        User user = userRepository.findUserByEmail(userService.getAuthenticatedUserEmail());

        scheduleService.registerSchedule(new Schedule(data.company_id(), user.getUser_id(), data.dateTime()));

        return ResponseEntity.ok().build();
    }

    @GetMapping
        public ResponseEntity<?> getSchedule(@RequestParam(required = false) int userId, @RequestParam(required = false) LocalDateTime DateTimeInit, @RequestParam(required = false) LocalDateTime DateTimeEnd) {
        User user = userRepository.findUserByEmail(userService.getAuthenticatedUserEmail());

        List<Schedule> response = new ArrayList<>();

        if (userId >= 0) {
            List<Schedule> schedules = scheduleRepository.findAllByUserId(userId);

            schedules.forEach(schedule -> {
                companyRepository.findById(schedule.getCompany_id())
                        .ifPresent(company -> {
                            if (company.getOwner() == user.getUser_id()) {
                                response.add(schedule);
                            }
                        });
            });
        }

        if (DateTimeInit != null) {
            List<Schedule> schedules = scheduleRepository.findAll();

            schedules.forEach(schedule -> {
                companyRepository.findById(schedule.getCompany_id())
                        .ifPresent(company -> {
                            if (company.getOwner() == user.getUser_id() && schedule.getDateTime().isAfter(DateTimeInit)) {
                                response.add(schedule);
                            }
                        });
            });
        }

        if (DateTimeEnd != null) {
            List<Schedule> schedules = scheduleRepository.findAll();

            schedules.forEach(schedule -> {
                companyRepository.findById(schedule.getCompany_id())
                        .ifPresent(company -> {
                            if (company.getOwner() == user.getUser_id() && schedule.getDateTime().isBefore(DateTimeEnd)) {
                                response.add(schedule);
                            }
                        });
            });
        }

        return ResponseEntity.ok(response);
    }
}