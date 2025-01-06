package com.ramon.pontes.RmPlannerApi.dto.request;

import com.ramon.pontes.RmPlannerApi.model.User.UserRole;

public record RegisterRequest(String email, String password, String username, UserRole account_type) {
}
