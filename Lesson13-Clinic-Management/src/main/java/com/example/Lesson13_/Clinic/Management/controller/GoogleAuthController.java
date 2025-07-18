package com.example.Lesson13_.Clinic.Management.controller;

import com.example.Lesson13_.Clinic.Management.repository.UserRepository;
import com.example.Lesson13_.Clinic.Management.security.JwtService;
import com.example.Lesson13_.Clinic.Management.service.GoogleOAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.Lesson13_.Clinic.Management.entity.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/auth")
public class GoogleAuthController {
    private final GoogleOAuthService googleOAuthService;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public GoogleAuthController(GoogleOAuthService googleOAuthService, UserRepository userRepository, JwtService jwtService) {
        this.googleOAuthService = googleOAuthService;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    private static final Logger logger = LoggerFactory.getLogger(GoogleAuthController.class);

    @GetMapping("/google/login")
    public ResponseEntity<?> loginWithGoogle() {
        String url = googleOAuthService.getGoogleOAuthUrl();
        logger.info("Generated Google login URL: {}", url);
        return ResponseEntity.ok(Map.of("url", url));
    }

    @GetMapping("/google/callback")
    public ResponseEntity<?> googleCallback(@RequestParam("code") String code) {
        try {
            logger.info("Received Google callback with code: {}", code);

            String accessToken = googleOAuthService.getAccessToken(code);
            Map<String, Object> userInfo = googleOAuthService.getUserInfo(accessToken);

            String email = (String) userInfo.get("email");
            logger.info("Email from Google: {}", email);

            Optional<User> optionalUser = userRepository.findByEmail(email);
            if (optionalUser.isEmpty()) {
                logger.warn("Email {} not found in system", email);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Email chưa đăng ký trong hệ thống.");
            }

            User user = optionalUser.get();

            //Chỉ cần sinh JWT và trả về
            String token = jwtService.generateToken(user);

            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "user", user
            ));
        } catch (Exception e) {
            logger.error("Google login callback error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi xử lý callback Google: " + e.getMessage());
        }
    }

}
