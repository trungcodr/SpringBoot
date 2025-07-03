package com.example.login_logout1.mapper;

import com.example.login_logout1.dto.RegisterRequest;
import com.example.login_logout1.entity.User;

import java.time.LocalDate;

public class UserMapper {
    public static User fromRegisterRequest(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setCreatedAt(LocalDate.now());
        user.setRoleId(request.getRoleId());
        return user;
    }
}
