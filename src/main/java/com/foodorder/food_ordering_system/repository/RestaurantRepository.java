package com.foodorder.food_ordering_system.repository;

import com.foodorder.food_ordering_system.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByCuisineContainingIgnoreCase(String cuisine);
}