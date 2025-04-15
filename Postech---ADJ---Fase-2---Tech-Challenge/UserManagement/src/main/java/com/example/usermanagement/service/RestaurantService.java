package com.example.usermanagement.service;

import com.example.usermanagement.model.Restaurant;
import com.example.usermanagement.model.User;
import com.example.usermanagement.repository.RestaurantRepository;
import com.example.usermanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    public Restaurant createRestaurant(Restaurant restaurant, Long ownerId) {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found with id: " + ownerId));
        restaurant.setOwner(owner);
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));
    }

    public Restaurant updateRestaurant(Long id, Restaurant updated) {
        Restaurant existing = getRestaurantById(id);
        existing.setName(updated.getName());
        existing.setAddress(updated.getAddress());
        existing.setCuisineType(updated.getCuisineType());
        existing.setOpeningHours(updated.getOpeningHours());

        if (updated.getOwner() != null) {
            User newOwner = userRepository.findById(updated.getOwner().getId())
                    .orElseThrow(() -> new RuntimeException("New owner not found with id: " + updated.getOwner().getId()));
            existing.setOwner(newOwner);
        }

        return restaurantRepository.save(existing);
    }

    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }
}
