package com.ramon.pontes.RmPlannerApi.controller;

import com.ramon.pontes.RmPlannerApi.dto.request.schedule.RegisterScheduleRequest;
import com.ramon.pontes.RmPlannerApi.exception.NotFoundException;
import com.ramon.pontes.RmPlannerApi.model.Schedule.Schedule;
import com.ramon.pontes.RmPlannerApi.model.User.User;
import com.ramon.pontes.RmPlannerApi.repository.UserRepository;
import com.ramon.pontes.RmPlannerApi.service.ScheduleService;
import com.ramon.pontes.RmPlannerApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(NotFoundException ex) {
        return ex.getMessage();
    }

    @PostMapping
    public ResponseEntity<?> registerSchedule(@RequestBody RegisterScheduleRequest data) {
        User user = userRepository.findUserByEmail(userService.getAuthenticatedUserEmail());

        scheduleService.registerSchedule(new Schedule(data.company_id(), user.getUser_id()));

        return ResponseEntity.ok().build();
    }
}
