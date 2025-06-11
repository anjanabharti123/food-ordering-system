package com.foodorder.food_ordering_system.repository;

import com.foodorder.food_ordering_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}