package com.example.login_logout1.service;

import com.example.login_logout1.dto.LoginRequest;
import com.example.login_logout1.dto.RegisterRequest;
import com.example.login_logout1.dto.UserResponse;
import com.example.login_logout1.entity.Role;
import com.example.login_logout1.entity.User;
import com.example.login_logout1.mapper.UserMapper;
import com.example.login_logout1.repository.RoleRepository;
import com.example.login_logout1.repository.UserRepository;
import com.example.login_logout1.security.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtUtil = jwtUtil;
    }


    public void register(RegisterRequest request) {
        log.info(">> register() START - Request: {}", request);
        // Kiem tra username da ton tai hay chua
        if (userRepository.existsByUsername(request.getUsername())) {
            log.warn("Username `{}` da ton tai", request.getUsername());
            throw new RuntimeException("Username da ton tai");
        }

        // Kiem tra emil da ton tai hay chua
        if (userRepository.existsByEmail(request.getEmail())) {
            log.warn("Email `{}` da ton tai", request.getEmail());
            throw new RuntimeException("Email da ton tai");
        }

        // Kiem tra phone da ton tai hay chua
        if (userRepository.existsByPhone(request.getPhone())) {
            log.warn("Phone `{}` da ton tai", request.getPhone());
            throw new RuntimeException("So dien thoai da ton tai");
        }

        // Kiem tra role da ton tai hay chua
        if (!roleRepository.existsById(request.getRoleId())) {
            log.warn("Khong tim thay Role voi Id: {}", request.getRoleId());
            throw new RuntimeException("Role khong tont tai!");
        }

        User user = UserMapper.fromRegisterRequest(request);
        userRepository.save(user);
        log.info("<< register() SUCCESS - User '{}' da duoc dang ky thanh cong", request.getUsername());
    }

    public String login(LoginRequest request) {
        log.info(">> login() START - {}", request);
        User user = userRepository.findByUsername(request.getUsername());
        if (user == null) {
            log.warn("Username `{}` khong ton tai", request.getUsername());
            throw new RuntimeException("Sai username hoac password");
        }
        if (!user.getPassword().equals(request.getPassword())) {
            log.warn("Mat khau khong dung cho user `{}` ", request.getUsername());
            throw new RuntimeException("Sai username hoac password");
        }
        String token = jwtUtil.generateToken(user.getUsername());
        log.info("<< login() SUCCESS - JWT created for `{}` ", user.getUsername());
        return token;
    }

    //    public User getUserByUsername(String username) {
//        if (username == null) throw new RuntimeException("Token khong chua username");
//        User user = userRepository.findByUsername(username);
//        if (user == null) throw new RuntimeException("Khong tim thay user");
//        return user;
//    }
    public UserResponse getUserInfo(String username) {
        log.info(">> getUserInfo() START - username: {}", username);

        if (username == null) throw new RuntimeException("Token không chứa username");

        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.warn("Không tìm thấy user với username: {}", username);
            throw new RuntimeException("User không tồn tại");
        }

        // Lấy role
        Role role = null;
        if (user.getRoleId() != null) {
            role = roleRepository.findById(user.getRoleId()).orElse(null);
        }

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setAddress(user.getAddress());
        response.setRoleName(role != null ? role.getName() : null);

        log.info("<< getUserInfo() SUCCESS - user: {}", response.getUsername());
        return response;
    }
}
