package com.Portofolio.Absensi_karyawan.handler;

import java.time.Instant;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Portofolio.Absensi_karyawan.dto.KaryawanErrorResponse;
import com.Portofolio.Absensi_karyawan.exception.KaryawanAlreadyExistsException;
import com.Portofolio.Absensi_karyawan.exception.KaryawanInvalidRequestException;
import com.Portofolio.Absensi_karyawan.exception.KaryawanNotFoundException;

@RestControllerAdvice
public class KaryawanExceptionHandler {
    
    @ExceptionHandler(KaryawanNotFoundException.class)
    public ResponseEntity<KaryawanErrorResponse> handleNotFound(KaryawanNotFoundException ex) {
        return build(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(KaryawanAlreadyExistsException.class)
    public ResponseEntity<KaryawanErrorResponse> handleAlreadyExists(KaryawanAlreadyExistsException ex) {
        return build(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(KaryawanInvalidRequestException.class)
    public ResponseEntity<KaryawanErrorResponse> handleInvalid(KaryawanInvalidRequestException ex) {
        return build(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    // Jaring pengaman jika dua request bersamaan lolos pengecekan di service
    // tetapi ditolak oleh constraint unik/foreign key di database.
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<KaryawanErrorResponse> handleIntegrity(DataIntegrityViolationException ex) {
        return build(HttpStatus.CONFLICT, "Data bertentangan dengan data yang sudah ada");
    }

    private ResponseEntity<KaryawanErrorResponse> build(HttpStatus status, String message) {
        KaryawanErrorResponse body = new KaryawanErrorResponse(
                Instant.now(), status.value(), status.getReasonPhrase(), message, null);
        return ResponseEntity.status(status).body(body);
    }
}
