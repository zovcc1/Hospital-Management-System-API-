package com.hospital.management.system.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hospital.management.system.dto.DepartmentDto;
import com.hospital.management.system.exceptions.AlreadyExistsException;
import com.hospital.management.system.exceptions.ResourceNotFoundException;
import com.hospital.management.system.mapper.DepartmentMapper;
import com.hospital.management.system.model.Department;
import com.hospital.management.system.repository.DepartmentRepository;
import com.hospital.management.system.repository.RoomRepository;

@Service
public class DepartmentService {
    @SuppressWarnings("unused")
    private static final Logger log = LogManager.getLogger(DepartmentService.class);
    private final DepartmentRepository departmentRepository;
    private final RoomRepository roomRepository;


    public DepartmentService(DepartmentRepository departmentRepository, RoomRepository roomRepository) {
        this.departmentRepository = departmentRepository;
        this.roomRepository = roomRepository;
    }


    public List<DepartmentDto> getAllDepartments() {
        List<Department> department = departmentRepository.findAll();
        return DepartmentMapper.DEPARTMENT_MAPPER.toDto(department);

    }

    public DepartmentDto getDepartment(Integer id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("department not found"));
        return DepartmentMapper.DEPARTMENT_MAPPER.toDto(department);
    }

    public DepartmentDto createDepartment(Department newDepartment) {
        return Optional.of(newDepartment).filter(department1 -> !departmentRepository.existsByName(newDepartment.getName())).map(departmentRepository::save).map(department -> DepartmentMapper.DEPARTMENT_MAPPER.toDto(department)).orElseThrow(() -> new AlreadyExistsException("department already exists!"));

    }

    public DepartmentDto updateDepartment(DepartmentDto dto) {
        //   look for department ID in the database
        Department department = departmentRepository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("department not found"));
        // map dto to entity before saving.
        Department updatedDepartment = DepartmentMapper.DEPARTMENT_MAPPER.partialUpdate(dto, department);
        // save to repo
        department = departmentRepository.save(updatedDepartment);

        return DepartmentMapper.DEPARTMENT_MAPPER.toDto(department);
    }


    public void deleteDepartment(Integer id) {
        departmentRepository.findById(id).ifPresentOrElse(departmentRepository::delete, () -> {
            throw new ResourceNotFoundException("Department not found.");
        });
    }


}
