package com.Portofolio.Absensi_karyawan.dto;

import java.time.Instant;
import java.util.Map;

public record KaryawanErrorResponse (
    Instant timestamp,
        int status,
        String error,
        String message,
        Map<String, String> fieldErrors
){}
