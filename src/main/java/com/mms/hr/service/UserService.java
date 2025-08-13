package com.mms.hr.service;

import com.mms.hr.entity.User;
import com.mms.hr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public List<User> getAllActiveUsers() {
        return userRepository.findByIsActiveTrue();
    }
    
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
    
    public List<User> getPotentialApprovers() {
        return userRepository.findPotentialApprovers();
    }
    
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
