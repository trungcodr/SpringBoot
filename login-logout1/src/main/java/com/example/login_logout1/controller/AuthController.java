package com.example.login_logout1.controller;

import com.example.login_logout1.dto.LoginRequest;
import com.example.login_logout1.dto.RegisterRequest;
import com.example.login_logout1.dto.UserResponse;
import com.example.login_logout1.entity.User;
import com.example.login_logout1.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        logger.info("[API] POST /auth/register - Payload: {}", request);
        try {
            userService.register(request);
            logger.info("[API] Dang ky thanh cong cho user: {}", request.getUsername());
            return ResponseEntity.ok("Dang ky thanh cong");
        } catch (RuntimeException e) {
            logger.error("[API] Loi khi dang ky: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        logger.info("[API] POST /auth/login - Payload: {}", request);
        try {
            String token = userService.login(request);
            logger.info("[API] Đăng nhập thành công - token trả về");
            return ResponseEntity.ok(token);
        } catch (RuntimeException e) {
            logger.error("[API] Lỗi đăng nhập: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {
        String username = (String) request.getAttribute("currentUser");
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Chưa đăng nhập");
        }

        UserResponse userInfo = userService.getUserInfo(username);
        return ResponseEntity.ok(userInfo);
    }
}
