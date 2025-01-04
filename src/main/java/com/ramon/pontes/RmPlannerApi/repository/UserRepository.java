package com.ramon.pontes.RmPlannerApi.repository;

import com.ramon.pontes.RmPlannerApi.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Integer> {
    UserDetails findByEmail(String email);
}