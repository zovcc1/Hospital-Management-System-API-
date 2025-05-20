package com.hospital.management.system.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.hospital.management.system.dto.DoctorDto;
import com.hospital.management.system.model.Doctor;

@Mapper
public interface DoctorMapper {
    DoctorMapper DOCTOR_MAPPER = Mappers.getMapper(DoctorMapper.class);

    @Mapping(target = "department.id", source = "departmentId")
    Doctor toEntity(DoctorDto doctorDto);

    List<Doctor> toEntity(List<DoctorDto> doctorDto);

    @Mapping(target = "departmentId", source = "department.id")
    DoctorDto toDto(Doctor doctor);

    List<DoctorDto> toDto(List<Doctor> doctor);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Doctor partialUpdate(DoctorDto doctorDto, @MappingTarget Doctor doctor);

}
