package com.hospital.management.system.mapper;

import com.hospital.management.system.dto.ReservationDto;
import com.hospital.management.system.model.Reservation;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ReservationMapper {
    ReservationMapper MAPPER = Mappers.getMapper(ReservationMapper.class);
    @Mapping(target = "patientFile.id" , source = "patientFileId")
    @Mapping(target = "room.id" , source = "roomId")
    @Mapping(target = "doctor.id" , source = "doctorId")
@Mapping(source = "patientName" , target = "patientFile.name")
@Mapping(source = "doctorName", target = "doctor.name")
@Mapping(source = "doctorSpecialization" , target = "doctor.specialty")
    Reservation toEntity(ReservationDto reservationDto);

    List<Reservation> toEntity(List<ReservationDto> reservationDto);
        @Mapping(source = "patientFile.id" , target = "patientFileId")
    @Mapping(source = "doctor.id" , target = "doctorId")
    @Mapping(source = "room.id" , target = "roomId")
    @Mapping(target = "patientName" , source = "patientFile.name")
    @Mapping(target = "doctorName", source = "doctor.name")
    @Mapping(target = "doctorSpecialization" , source = "doctor.specialty")
    ReservationDto toDto(Reservation reservation);

    List<ReservationDto> toDto(List<Reservation> reservation);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)Reservation partialUpdate(ReservationDto reservationDto, @MappingTarget Reservation reservation);
}
