package com.Portofolio.Absensi_karyawan.dto;

import com.Portofolio.Absensi_karyawan.constant.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(
    @NotBlank(message = "Username wajib diisi")
        @Size(min = 3, max = 50, message = "Username harus 3-50 karakter")
        String username,

        @NotBlank(message = "Password wajib diisi")
        @Size(min = 8, max = 72, message = "Password harus 8-72 karakter")
        String password,

        @NotNull(message = "Role wajib diisi")
        Role role
) {} 
