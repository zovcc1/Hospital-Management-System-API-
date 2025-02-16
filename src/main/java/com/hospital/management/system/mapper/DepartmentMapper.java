package com.hospital.management.system.mapper;

import com.hospital.management.system.dto.DepartmentDto;
import com.hospital.management.system.model.Department;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    DepartmentMapper DEPARTMENT_MAPPER = Mappers.getMapper(DepartmentMapper.class);
    Department toEntity(DepartmentDto departmentDto);

    List<Department> toEntity(List<DepartmentDto> departmentDto);

    DepartmentDto toDto(Department department);

    List<DepartmentDto> toDto(List<Department> department);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)Department partialUpdate(DepartmentDto departmentDto, @MappingTarget Department department);
}
