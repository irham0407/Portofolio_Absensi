package com.Portofolio.Absensi_karyawan.dto;

import com.Portofolio.Absensi_karyawan.constant.Role;

public record UserUpdateRequest(
    Role role,
    Boolean enabled
) {}
