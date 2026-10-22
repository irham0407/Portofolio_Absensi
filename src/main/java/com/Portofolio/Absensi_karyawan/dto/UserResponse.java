package com.Portofolio.Absensi_karyawan.dto;

import java.time.Instant;

import com.Portofolio.Absensi_karyawan.constant.Role;
import com.Portofolio.Absensi_karyawan.model.User;

public record UserResponse(
    Long id,
    String username,
    Role role,
    boolean enabled,
    Instant createdAt,
    Instant updatedAt
) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                user.isEnabled(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }
}
