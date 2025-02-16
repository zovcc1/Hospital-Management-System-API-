package com.hospital.management.system.service;

import com.hospital.management.system.dto.DoctorDto;

import java.util.List;

public interface DoctorService {
    DoctorDto createDoctor(DoctorDto request);
    List<DoctorDto> getAllAvailableDoctors();
    List<DoctorDto> getAllDoctors();
    DoctorDto getDoctorById(Integer id);
    DoctorDto updateDoctor (DoctorDto updateDto);
    void deleteDoctorById(Integer id);

}
