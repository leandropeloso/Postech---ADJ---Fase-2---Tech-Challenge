package com.example.usermanagement.service;

import com.example.usermanagement.model.UserType;
import com.example.usermanagement.repository.UserTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserTypeService {

    private final UserTypeRepository userTypeRepository;

    public UserTypeService(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    public UserType createUserType(UserType userType) {
        return userTypeRepository.save(userType);
    }

    public List<UserType> getAllUserTypes() {
        return userTypeRepository.findAll();
    }

    public Optional<UserType> getUserTypeById(Long id) {
        return userTypeRepository.findById(id);
    }

    public UserType updateUserType(Long id, UserType updatedType) {
        UserType existing = userTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User type not found with id: " + id));
        existing.setName(updatedType.getName());
        return userTypeRepository.save(existing);
    }

    public void deleteUserType(Long id) {
        userTypeRepository.deleteById(id);
    }
}
