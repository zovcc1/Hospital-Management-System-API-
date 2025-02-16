package com.hospital.management.system.mapper;

import com.hospital.management.system.dto.PatientRoomDto;
import com.hospital.management.system.model.PatientRoom;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PatientRoomMapper {

    PatientRoomMapper MAPPER = Mappers.getMapper(PatientRoomMapper.class);
    @Mapping(source = "roomId" , target = "room.id")
    @Mapping(source = "patientFileId" , target = "patientFile.id")
    PatientRoom toEntity(PatientRoomDto patientRoomDto);

    List<PatientRoom> toEntity(List<PatientRoomDto> patientRoomDto);
    @Mapping(source = "room.id" , target = "roomId")
    @Mapping(source = "patientFile.id", target = "patientFileId")
    PatientRoomDto toDto(PatientRoom patientRoom);

    List<PatientRoomDto> toDto(List<PatientRoom> patientRoom);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)PatientRoom partialUpdate(PatientRoomDto patientRoomDto, @MappingTarget PatientRoom patientRoom);
}
