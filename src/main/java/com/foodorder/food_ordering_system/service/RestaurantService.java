package com.foodorder.food_ordering_system.service;

import com.foodorder.food_ordering_system.entity.Restaurant;
import com.foodorder.food_ordering_system.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> getRestaurantById(Long id) {
        return restaurantRepository.findById(id);
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Optional<Restaurant> updateRestaurant(Long id, Restaurant updatedRestaurant) {
        Optional<Restaurant> existing = restaurantRepository.findById(id);
        if (existing.isPresent()) {
            Restaurant restaurant = existing.get();
            restaurant.setName(updatedRestaurant.getName());
            restaurant.setAddress(updatedRestaurant.getAddress());
            restaurant.setCuisine(updatedRestaurant.getCuisine());
            return Optional.of(restaurantRepository.save(restaurant));
        }
        return Optional.empty();
    }

    public boolean deleteRestaurant(Long id) {
        if (restaurantRepository.existsById(id)) {
            restaurantRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Restaurant> searchRestaurantsByCuisine(String cuisine) {
        return restaurantRepository.findByCuisineContainingIgnoreCase(cuisine);
    }
}