package com.example.Lesson13_.Clinic.Management.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    /**
     * Lấy email (username) của người dùng hiện tại từ context bảo mật.
     * Nếu chưa xác thực hoặc không tồn tại, trả về null.
     */
    public static String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()
                && !"anonymousUser".equals(authentication.getPrincipal())) {
            return authentication.getName(); // chính là email nếu bạn dùng email để login
        }

        return null;
    }
}
