package com.ramon.pontes.RmPlannerApi.repository;

import com.ramon.pontes.RmPlannerApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}