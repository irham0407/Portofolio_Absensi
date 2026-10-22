package com.Portofolio.Absensi_karyawan.exception;

public class KaryawanNotFoundException  extends RuntimeException {
    
    public KaryawanNotFoundException(Long id) {
        super("Karyawan dengan id " + id + " tidak ditemukan");
    }
}
