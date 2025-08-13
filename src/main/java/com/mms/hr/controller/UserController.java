package com.mms.hr.controller;

import com.mms.hr.entity.User;
import com.mms.hr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllActiveUsers();
        System.out.println("👥 Returning " + users.size() + " active users");
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            System.out.println("👤 Returning user: " + user.getName());
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            System.err.println("❌ User not found with ID: " + id);
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/users/approvers")
    public ResponseEntity<List<User>> getPotentialApprovers() {
        List<User> approvers = userService.getPotentialApprovers();
        System.out.println("👔 Returning " + approvers.size() + " potential approvers");
        return ResponseEntity.ok(approvers);
    }
}
