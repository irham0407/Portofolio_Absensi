package com.Portofolio.Absensi_karyawan;

import org.springframework.boot.SpringApplication;

public class TestAbsensiKaryawanApplication {

	public static void main(String[] args) {
		SpringApplication.from(AbsensiKaryawanApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
