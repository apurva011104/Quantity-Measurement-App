package com.apps.quantity_measurement_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apps.quantity_measurement_app.entity.User;


public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
}
