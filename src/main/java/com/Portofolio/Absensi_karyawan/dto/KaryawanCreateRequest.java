package com.Portofolio.Absensi_karyawan.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public record KaryawanCreateRequest (
@NotNull(message = "userId wajib diisi")
        Long userId,

        @NotBlank(message = "NIK wajib diisi")
        @Size(max = 30, message = "NIK maksimal 30 karakter")
        String nik,

        @NotBlank(message = "Nama lengkap wajib diisi")
        @Size(max = 100, message = "Nama lengkap maksimal 100 karakter")
        String namaLengkap,

        @NotBlank(message = "Email wajib diisi")
        @Email(message = "Format email tidak valid")
        @Size(max = 100, message = "Email maksimal 100 karakter")
        String email,

        @Pattern(regexp = "^\\+?\\d{8,15}$", message = "Nomor telepon harus 8-15 digit")
        String noTelepon,

        @NotBlank(message = "Jabatan wajib diisi")
        @Size(max = 100, message = "Jabatan maksimal 100 karakter")
        String jabatan,

        @NotBlank(message = "Departemen wajib diisi")
        @Size(max = 100, message = "Departemen maksimal 100 karakter")
        String departemen,

        @NotNull(message = "Tanggal bergabung wajib diisi")
        @PastOrPresent(message = "Tanggal bergabung tidak boleh di masa depan")
        LocalDate tanggalBergabung,

        Long managerId
){}
