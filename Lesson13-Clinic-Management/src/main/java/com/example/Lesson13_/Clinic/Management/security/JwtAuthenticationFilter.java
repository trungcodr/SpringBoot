package com.example.Lesson13_.Clinic.Management.security;

import com.example.Lesson13_.Clinic.Management.entity.User;
import com.example.Lesson13_.Clinic.Management.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                if (jwtService.validateToken(token)) {
                    String email = jwtService.extractUsername(token); // thường là email

                    User user = userRepository.findByEmail(email)
                            .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

                    // Gắn ROLE_ nếu chưa có
                    String role = user.getRole() != null && user.getRole().startsWith("ROLE_")
                            ? user.getRole()
                            : "ROLE_" + user.getRole();

                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    user,
                                    null,
                                    List.of(new SimpleGrantedAuthority(role))
                            );

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    logger.info("Xác thực token thành công cho người dùng: {}", email);
                }
            } catch (Exception e) {
                logger.error("Lỗi xác thực JWT: {}", e.getMessage(), e);
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Token không hợp lệ: " + e.getMessage());
                return; // Dừng filter chain nếu token lỗi
            }
        }

        filterChain.doFilter(request, response);
    }
}
