package com.ramon.pontes.RmPlannerApi.dto;

import com.ramon.pontes.RmPlannerApi.model.User.UserRole;

public record RegisterDTO(String email, String password, String username, UserRole account_type) {
}
