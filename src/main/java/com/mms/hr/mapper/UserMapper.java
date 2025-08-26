package com.mms.hr.mapper;

import com.mms.hr.DTO.UserDTO;
import com.mms.hr.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserDTO dto) {
        if (dto == null) return null;

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        user.setDepartment(dto.getDepartment());
        user.setManagerId(dto.getManagerId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword()); // Có thể mã hóa bằng BCrypt trước khi set
        user.setIsActive(true);

        return user;
    }
}
