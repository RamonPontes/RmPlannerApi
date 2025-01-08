package com.ramon.pontes.RmPlannerApi.repository;

import com.ramon.pontes.RmPlannerApi.model.Schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
}
