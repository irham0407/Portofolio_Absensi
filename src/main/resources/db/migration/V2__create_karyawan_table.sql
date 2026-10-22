CREATE TABLE karyawan (
    id                  BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id             BIGINT       NOT NULL,
    nik                 VARCHAR(30)  NOT NULL,
    nama_lengkap        VARCHAR(100) NOT NULL,
    email               VARCHAR(100) NOT NULL,
    no_telepon          VARCHAR(20),
    jabatan             VARCHAR(100) NOT NULL,
    departemen          VARCHAR(100) NOT NULL,
    tanggal_bergabung   DATE         NOT NULL,
    manager_id          BIGINT,
    aktif               BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at          TIMESTAMPTZ  NOT NULL DEFAULT NOW(),
    updated_at          TIMESTAMPTZ  NOT NULL DEFAULT NOW(),

    CONSTRAINT uk_karyawan_user  UNIQUE (user_id),
    CONSTRAINT uk_karyawan_nik   UNIQUE (nik),
    CONSTRAINT uk_karyawan_email UNIQUE (email),
    CONSTRAINT fk_karyawan_user    FOREIGN KEY (user_id)    REFERENCES users (id),
    CONSTRAINT fk_karyawan_manager FOREIGN KEY (manager_id) REFERENCES karyawan (id),
    CONSTRAINT ck_karyawan_bukan_manager_diri_sendiri
        CHECK (manager_id IS NULL OR manager_id <> id)
);

CREATE INDEX idx_karyawan_manager_id ON karyawan (manager_id);