package com.Portofolio.Absensi_karyawan.dto;

import java.time.Instant;
import java.time.LocalDate;

import com.Portofolio.Absensi_karyawan.model.Karyawan;

public record KaryawanResponse(
     Long id,
        Long userId,
        String username,
        String nik,
        String namaLengkap,
        String email,
        String noTelepon,
        String jabatan,
        String departemen,
        LocalDate tanggalBergabung,
        Long managerId,
        String managerNama,
        boolean aktif,
        Instant createdAt,
        Instant updatedAt
) {
    public static KaryawanResponse from(Karyawan k) {
        return new KaryawanResponse(
                k.getId(),
                k.getUser().getId(),
                k.getUser().getUsername(),
                k.getNik(),
                k.getNamaLengkap(),
                k.getEmail(),
                k.getNoTelepon(),
                k.getJabatan(),
                k.getDepartemen(),
                k.getTanggalBergabung(),
                k.getManager() != null ? k.getManager().getId() : null,
                k.getManager() != null ? k.getManager().getNamaLengkap() : null,
                k.isAktif(),
                k.getCreatedAt(),
                k.getUpdatedAt());
    }
}
