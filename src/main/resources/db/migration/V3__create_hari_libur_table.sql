CREATE TABLE hari_libur (
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    tanggal     DATE         NOT NULL,
    keterangan  VARCHAR(150) NOT NULL,
    jenis       VARCHAR(20)  NOT NULL,
    created_at  TIMESTAMPTZ  NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMPTZ  NOT NULL DEFAULT NOW(),

    CONSTRAINT uk_hari_libur_tanggal UNIQUE (tanggal),
    CONSTRAINT ck_hari_libur_jenis
        CHECK (jenis IN ('LIBUR_NASIONAL', 'CUTI_BERSAMA', 'LIBUR_PERUSAHAAN'))
);