package com.vti.blogapp.dto;

import com.vti.blogapp.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDto {
    private Long id;

    private String name;

    private String userName;

    private String email;

    private String password;

    private User.Role role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
