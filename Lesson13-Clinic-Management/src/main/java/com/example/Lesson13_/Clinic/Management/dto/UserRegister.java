package com.example.Lesson13_.Clinic.Management.dto;

import com.example.Lesson13_.Clinic.Management.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.aspectj.bridge.IMessage;

public class UserRegister {

    private String name;

    @jakarta.validation.constraints.Email(message = "Email khong hop le!")
    @NotBlank(message = "Email khong duoc de trong!")
    private String email;

    @NotBlank(message = "Password khong duoc de trong!")
    private String password;

    @NotNull(message = "Role khong duoc de trong!")
    private UserRole role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
