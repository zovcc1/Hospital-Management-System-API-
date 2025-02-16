package com.hospital.management.system.service;

import com.hospital.management.system.dto.DepartmentDto;
import com.hospital.management.system.dto.DoctorDto;
import com.hospital.management.system.exceptions.AlreadyExistsException;
import com.hospital.management.system.exceptions.ResourceNotFoundException;
import com.hospital.management.system.mapper.DoctorMapper;
import com.hospital.management.system.model.Doctor;
import com.hospital.management.system.repository.DoctorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService{
    private final DepartmentService departmentService;
    private final DoctorRepository doctorRepository;
    public DoctorServiceImpl(DepartmentService departmentService, DoctorRepository doctorRepository) {
        this.departmentService = departmentService;
        this.doctorRepository = doctorRepository;
    }


    /**
     * @param request 
     * @return
     */
    @Override
    @Transactional
    public DoctorDto createDoctor(@RequestBody DoctorDto request) {
        DepartmentDto departmentDto = departmentService.getDepartment(request.getDepartmentId());
        request.setDepartmentId(departmentDto.getId());
        return Optional.of(request)
                .map(doctorDto -> DoctorMapper.DOCTOR_MAPPER.toEntity(doctorDto))
                .map(doctorRepository::save)
                .map(doctor -> DoctorMapper.DOCTOR_MAPPER.toDto(doctor))
                .orElseThrow(() -> new AlreadyExistsException("Doctor already exists"));
    }

    /**
     * @return 
     */
    @Override
    public List<DoctorDto> getAllAvailableDoctors() {
        return doctorRepository.findAvailableDoctors(LocalTime.from(LocalDateTime.now())).stream().map(doctor -> DoctorMapper.DOCTOR_MAPPER.toDto(doctor)).collect(Collectors.toList());
    }

    /**
     * @return 
     */
    @Override
    public List<DoctorDto> getAllDoctors() {
        return doctorRepository.findAll().stream().map(doctor -> DoctorMapper.DOCTOR_MAPPER.toDto(doctor)).collect(Collectors.toList());
    }

    /**
     * @param id 
     * @return
     */
    @Override
    public DoctorDto getDoctorById(Integer id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("doctor with id: " +
                id + " not found"));
        return DoctorMapper.DOCTOR_MAPPER.toDto(doctor);
    }

    /**
     * @param updateDto 
     * @return
     */
    @Override
    public DoctorDto updateDoctor(DoctorDto updateDto) {
        Doctor doctor = doctorRepository.findById(updateDto.getId()).orElseThrow(() -> new ResourceNotFoundException("doctor with id: " +
                updateDto.getId() + " not found" ));
        Doctor updatedDoctor = DoctorMapper.DOCTOR_MAPPER.partialUpdate(updateDto, doctor);
        Doctor savedDoctor = doctorRepository.save(updatedDoctor);
        return DoctorMapper.DOCTOR_MAPPER.toDto(savedDoctor);
    }

    /**
     * @param id 
     */
    @Override
    public void deleteDoctorById(Integer id) {
        doctorRepository.findById(id).ifPresentOrElse(doctorRepository::delete , () -> {throw new ResourceNotFoundException("doctor with id: " +
                id +" not found");});
    }
}
