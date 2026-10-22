package com.Portofolio.Absensi_karyawan.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Portofolio.Absensi_karyawan.model.Karyawan;

public interface KaryawanRepository extends JpaRepository<Karyawan, Long>{
    
    boolean existsByNik(String nik);

    boolean existsByEmail(String email);

    boolean existsByUserId(Long userId);

    Optional<Karyawan> findByUserId(Long userId);

    // Ambil user dan manager sekaligus supaya tidak terjadi query berulang (N+1)
    @Override
    @EntityGraph(attributePaths = {"user", "manager"})
    Page<Karyawan> findAll(Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"user", "manager"})
    Optional<Karyawan> findById(Long id);
}
