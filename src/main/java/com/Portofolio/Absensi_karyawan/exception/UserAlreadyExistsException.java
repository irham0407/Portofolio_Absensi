package com.Portofolio.Absensi_karyawan.exception;

public class UserAlreadyExistsException extends RuntimeException   {
    
    public UserAlreadyExistsException(String username) {
        super("Username '" + username + "' sudah dipakai");
    }
}
