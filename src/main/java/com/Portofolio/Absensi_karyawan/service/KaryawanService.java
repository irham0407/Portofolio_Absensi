package com.Portofolio.Absensi_karyawan.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Portofolio.Absensi_karyawan.dto.KaryawanCreateRequest;
import com.Portofolio.Absensi_karyawan.dto.KaryawanResponse;
import com.Portofolio.Absensi_karyawan.dto.KaryawanUpdateRequest;
import com.Portofolio.Absensi_karyawan.exception.KaryawanAlreadyExistsException;
import com.Portofolio.Absensi_karyawan.exception.KaryawanInvalidRequestException;
import com.Portofolio.Absensi_karyawan.exception.KaryawanNotFoundException;
import com.Portofolio.Absensi_karyawan.exception.UserNotFoundException;
import com.Portofolio.Absensi_karyawan.model.Karyawan;
import com.Portofolio.Absensi_karyawan.model.User;
import com.Portofolio.Absensi_karyawan.repository.KaryawanRepository;
import com.Portofolio.Absensi_karyawan.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KaryawanService {
    
    private final KaryawanRepository karyawanRepository;
    private final UserRepository userRepository;

    @Transactional
    public KaryawanResponse create(KaryawanCreateRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new UserNotFoundException(request.userId()));

        if (karyawanRepository.existsByUserId(user.getId())) {
            throw new KaryawanAlreadyExistsException(
                    "User dengan id " + user.getId() + " sudah terhubung dengan karyawan lain");
        }

        String nik = request.nik().trim();
        if (karyawanRepository.existsByNik(nik)) {
            throw new KaryawanAlreadyExistsException("NIK '" + nik + "' sudah dipakai");
        }

        String email = normalizeEmail(request.email());
        if (karyawanRepository.existsByEmail(email)) {
            throw new KaryawanAlreadyExistsException("Email '" + email + "' sudah dipakai");
        }

        Karyawan manager = request.managerId() == null ? null : findOrThrow(request.managerId());

        Karyawan karyawan = Karyawan.builder()
                .user(user)
                .nik(nik)
                .namaLengkap(request.namaLengkap().trim())
                .email(email)
                .noTelepon(request.noTelepon())
                .jabatan(request.jabatan().trim())
                .departemen(request.departemen().trim())
                .tanggalBergabung(request.tanggalBergabung())
                .manager(manager)
                .build();

        return KaryawanResponse.from(karyawanRepository.save(karyawan));
    }

    @Transactional(readOnly = true)
    public Page<KaryawanResponse> getAll(Pageable pageable) {
        return karyawanRepository.findAll(pageable).map(KaryawanResponse::from);
    }

    @Transactional(readOnly = true)
    public KaryawanResponse getById(Long id) {
        return KaryawanResponse.from(findOrThrow(id));
    }

    @Transactional
    public KaryawanResponse update(Long id, KaryawanUpdateRequest request) {
        Karyawan karyawan = findOrThrow(id);

        if (request.namaLengkap() != null) {
            karyawan.setNamaLengkap(request.namaLengkap().trim());
        }
        if (request.email() != null) {
            String email = normalizeEmail(request.email());
            if (!email.equals(karyawan.getEmail()) && karyawanRepository.existsByEmail(email)) {
                throw new KaryawanAlreadyExistsException("Email '" + email + "' sudah dipakai");
            }
            karyawan.setEmail(email);
        }
        if (request.noTelepon() != null) {
            karyawan.setNoTelepon(request.noTelepon());
        }
        if (request.jabatan() != null) {
            karyawan.setJabatan(request.jabatan().trim());
        }
        if (request.departemen() != null) {
            karyawan.setDepartemen(request.departemen().trim());
        }
        if (request.tanggalBergabung() != null) {
            karyawan.setTanggalBergabung(request.tanggalBergabung());
        }
        if (request.managerId() != null) {
            if (request.managerId().equals(id)) {
                throw new KaryawanInvalidRequestException("Karyawan tidak boleh menjadi manajer dirinya sendiri");
            }
            karyawan.setManager(findOrThrow(request.managerId()));
        }
        if (request.aktif() != null) {
            karyawan.setAktif(request.aktif());
        }

        return KaryawanResponse.from(karyawan);
    }

    private Karyawan findOrThrow(Long id) {
        return karyawanRepository.findById(id)
                .orElseThrow(() -> new KaryawanNotFoundException(id));
    }

    private String normalizeEmail(String email) {
        return email.trim().toLowerCase();
    }
}
