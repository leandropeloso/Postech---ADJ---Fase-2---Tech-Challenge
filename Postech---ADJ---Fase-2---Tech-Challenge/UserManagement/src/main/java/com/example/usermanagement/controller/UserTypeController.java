package com.example.usermanagement.controller;

import com.example.usermanagement.model.UserType;
import com.example.usermanagement.service.UserTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-types")
public class UserTypeController {

    private final UserTypeService userTypeService;

    public UserTypeController(UserTypeService userTypeService) {
        this.userTypeService = userTypeService;
    }

    @PostMapping
    public ResponseEntity<UserType> createUserType(@RequestBody UserType userType) {
        UserType created = userTypeService.createUserType(userType);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<UserType>> getAllUserTypes() {
        return ResponseEntity.ok(userTypeService.getAllUserTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserType> getUserTypeById(@PathVariable Long id) {
        return userTypeService.getUserTypeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserType> updateUserType(@PathVariable Long id, @RequestBody UserType userType) {
        return ResponseEntity.ok(userTypeService.updateUserType(id, userType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserType(@PathVariable Long id) {
        userTypeService.deleteUserType(id);
        return ResponseEntity.noContent().build();
    }
}
