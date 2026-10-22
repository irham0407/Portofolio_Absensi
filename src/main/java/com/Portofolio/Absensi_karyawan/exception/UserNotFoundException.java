package com.Portofolio.Absensi_karyawan.exception;

public class UserNotFoundException extends RuntimeException {

        public UserNotFoundException(Long id) {
        super("User dengan id " + id + " tidak ditemukan");
    }
}
