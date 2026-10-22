package com.Portofolio.Absensi_karyawan.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public record KaryawanUpdateRequest (
        @Pattern(regexp = "^\\S.*", message = "Nama lengkap tidak boleh kosong atau diawali spasi")
        @Size(max = 100, message = "Nama lengkap maksimal 100 karakter")
        String namaLengkap,

        @Email(message = "Format email tidak valid")
        @Size(max = 100, message = "Email maksimal 100 karakter")
        String email,

        @Pattern(regexp = "^\\+?\\d{8,15}$", message = "Nomor telepon harus 8-15 digit")
        String noTelepon,

        @Pattern(regexp = "^\\S.*", message = "Jabatan tidak boleh kosong atau diawali spasi")
        @Size(max = 100, message = "Jabatan maksimal 100 karakter")
        String jabatan,

        @Pattern(regexp = "^\\S.*", message = "Departemen tidak boleh kosong atau diawali spasi")
        @Size(max = 100, message = "Departemen maksimal 100 karakter")
        String departemen,

        @PastOrPresent(message = "Tanggal bergabung tidak boleh di masa depan")
        LocalDate tanggalBergabung,

        Long managerId,

        Boolean aktif
){  }
