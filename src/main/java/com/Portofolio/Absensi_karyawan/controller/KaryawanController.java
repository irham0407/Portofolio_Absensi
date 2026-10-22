package com.Portofolio.Absensi_karyawan.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Portofolio.Absensi_karyawan.dto.KaryawanCreateRequest;
import com.Portofolio.Absensi_karyawan.dto.KaryawanResponse;
import com.Portofolio.Absensi_karyawan.dto.KaryawanUpdateRequest;
import com.Portofolio.Absensi_karyawan.service.KaryawanService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/karyawan")
@RequiredArgsConstructor
public class KaryawanController {
    
    private final KaryawanService karyawanService;

    @PostMapping
    public ResponseEntity<KaryawanResponse> create(@Valid @RequestBody KaryawanCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(karyawanService.create(request));
    }

    @GetMapping
    public PagedModel<KaryawanResponse> getAll(Pageable pageable) {
        return new PagedModel<>(karyawanService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public KaryawanResponse getById(@PathVariable Long id) {
        return karyawanService.getById(id);
    }

    @PatchMapping("/{id}")
    public KaryawanResponse update(@PathVariable Long id,
                                   @Valid @RequestBody KaryawanUpdateRequest request) {
        return karyawanService.update(id, request);
    }
}
