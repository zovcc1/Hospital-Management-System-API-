package com.hospital.management.system.service;

import com.hospital.management.system.dto.PatientFileDto;
import com.hospital.management.system.exceptions.AlreadyExistsException;
import com.hospital.management.system.exceptions.ResourceNotFoundException;
import com.hospital.management.system.mapper.PatientFileMapper;
import com.hospital.management.system.model.PatientFile;
import com.hospital.management.system.repository.PatientFileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientFileServiceImp implements PatientFileService {

    private final PatientFileRepository patientFileRepository;

    public PatientFileServiceImp(PatientFileRepository patientFileRepository) {
        this.patientFileRepository = patientFileRepository;

    }


    @Override
    @Transactional
    public PatientFileDto createPatientFile(PatientFileDto request) {
        return Optional.of(request)
                .map(patientFileDto -> PatientFileMapper.MAPPER.toEntity(patientFileDto))
                .map(patientFileRepository::save)
                .map(patientFile -> PatientFileMapper.MAPPER.toDto(patientFile))
                .orElseThrow(() -> new AlreadyExistsException("already exists"));
    }

    @Override
    public List<PatientFileDto> getAllPatientFiles() {
        return patientFileRepository.findAll().stream().map(patientFile -> PatientFileMapper.MAPPER.toDto(patientFile)).collect(Collectors.toList());
    }

    @Override
    public PatientFileDto getPatientFileById(Integer patientId) {
        return PatientFileMapper.MAPPER.toDto(patientFileRepository.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("patient with id " +
                patientId + "not found")));
    }

    @Override
    public PatientFileDto updatePatientFile(PatientFileDto newPatientFile) {
        PatientFile patientfile = patientFileRepository.findById(newPatientFile.getId()).orElseThrow(() -> new ResourceNotFoundException("not found"));
        PatientFile patientFilePatch = PatientFileMapper.MAPPER.partialUpdate(newPatientFile,patientfile);
        PatientFile patientFile = patientFileRepository.save(patientFilePatch);
        return PatientFileMapper.MAPPER.toDto(patientFile);
    }

    @Override
    public void deletePatientFile(Integer patientId) {
        patientFileRepository.findById(patientId).ifPresentOrElse(patientFileRepository::delete , () -> {throw new ResourceNotFoundException("not found");});
    }
}
