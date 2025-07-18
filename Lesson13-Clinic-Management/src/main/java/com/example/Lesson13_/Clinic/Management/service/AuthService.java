package com.example.Lesson13_.Clinic.Management.service;

import com.example.Lesson13_.Clinic.Management.dto.Login;
import com.example.Lesson13_.Clinic.Management.dto.UserRegister;
import com.example.Lesson13_.Clinic.Management.entity.User;
import com.example.Lesson13_.Clinic.Management.repository.UserRepository;
import com.example.Lesson13_.Clinic.Management.security.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public String register(UserRegister userRegister) {
        if (userRepository.existsByEmail(userRegister.getEmail())) {
            logger.warn("Email đã tồn tại: {}", userRegister.getEmail());
            throw new IllegalArgumentException("Email đã được sử dụng!");
        }

        User user = new User();
        user.setName(userRegister.getName());
        user.setEmail(userRegister.getEmail());
        user.setPassword(passwordEncoder.encode(userRegister.getPassword()));
        user.setRole(userRegister.getRole().name());

        userRepository.save(user);

        logger.info("Đăng ký thành công cho email: {}", userRegister.getEmail());
        return "Đăng ký thành công";
    }

    public String login(Login request) {
//        Authentication auth = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//        );
//
//        return jwtService.generateToken(auth);
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            String token = jwtService.generateToken(authentication);
            logger.info(" Đăng nhập thành công cho: {}", request.getEmail());
            return token;

        } catch (BadCredentialsException e) {
            logger.warn("Đăng nhập thất bại (sai thông tin): {}", request.getEmail());
            throw new BadCredentialsException("Email hoặc mật khẩu không đúng!");
        }
    }
}
