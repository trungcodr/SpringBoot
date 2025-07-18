package com.example.Lesson13_.Clinic.Management.controller;

import com.example.Lesson13_.Clinic.Management.dto.Login;
import com.example.Lesson13_.Clinic.Management.dto.UserRegister;
import com.example.Lesson13_.Clinic.Management.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody UserRegister request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody Login request) {
        return authService.login(request);
    }
}
