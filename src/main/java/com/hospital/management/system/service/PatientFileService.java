package com.hospital.management.system.service;

import com.hospital.management.system.dto.PatientFileDto;

import java.util.List;

public interface PatientFileService {
     PatientFileDto createPatientFile(PatientFileDto request);
     List<PatientFileDto> getAllPatientFiles();
     PatientFileDto getPatientFileById(Integer patientId);
     PatientFileDto updatePatientFile(PatientFileDto newPatientFile);
     void deletePatientFile(Integer patientId);
}
