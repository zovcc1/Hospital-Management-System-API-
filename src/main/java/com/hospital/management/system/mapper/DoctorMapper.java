package com.hospital.management.system.mapper;
import com.hospital.management.system.dto.DoctorDto;
import com.hospital.management.system.model.Department;
import com.hospital.management.system.model.Doctor;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DoctorMapper {
    DoctorMapper DOCTOR_MAPPER = Mappers.getMapper(DoctorMapper.class);
    @Mapping(target = "department.id" , source = "departmentId")
    Doctor toEntity(DoctorDto doctorDto);

    List<Doctor> toEntity(List<DoctorDto> doctorDto);

    @Mapping(target = "departmentId" , source = "department.id")
    DoctorDto toDto(Doctor doctor);

    List<DoctorDto> toDto(List<Doctor> doctor);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)Doctor partialUpdate(DoctorDto doctorDto, @MappingTarget Doctor doctor);

}
