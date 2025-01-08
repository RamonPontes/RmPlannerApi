package com.ramon.pontes.RmPlannerApi.dto.request.schedule;

import java.time.LocalDateTime;

public record RegisterScheduleRequest(int company_id, LocalDateTime dateTime) {
}
