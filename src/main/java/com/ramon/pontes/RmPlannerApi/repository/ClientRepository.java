package com.ramon.pontes.RmPlannerApi.repository;

import com.ramon.pontes.RmPlannerApi.model.Client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
