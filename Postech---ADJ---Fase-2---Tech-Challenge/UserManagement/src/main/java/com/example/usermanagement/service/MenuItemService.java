package com.example.usermanagement.service;

import com.example.usermanagement.model.MenuItem;
import com.example.usermanagement.model.Restaurant;
import com.example.usermanagement.repository.MenuItemRepository;
import com.example.usermanagement.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuItemService(MenuItemRepository menuItemRepository, RestaurantRepository restaurantRepository) {
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public MenuItem createMenuItem(MenuItem menuItem, Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + restaurantId));
        menuItem.setRestaurant(restaurant);
        return menuItemRepository.save(menuItem);
    }

    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    public MenuItem getMenuItemById(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found with id: " + id));
    }

    public MenuItem updateMenuItem(Long id, MenuItem updatedItem) {
        MenuItem existingItem = getMenuItemById(id);
        existingItem.setName(updatedItem.getName());
        existingItem.setDescription(updatedItem.getDescription());
        existingItem.setPrice(updatedItem.getPrice());
        existingItem.setAvailableInRestaurant(updatedItem.getAvailableInRestaurant());
        existingItem.setPhotoPath(updatedItem.getPhotoPath());

        if (updatedItem.getRestaurant() != null) {
            Restaurant newRestaurant = restaurantRepository.findById(updatedItem.getRestaurant().getId())
                    .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + updatedItem.getRestaurant().getId()));
            existingItem.setRestaurant(newRestaurant);
        }

        return menuItemRepository.save(existingItem);
    }

    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }
}

