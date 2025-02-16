package com.hospital.management.system.mapper;

import com.hospital.management.system.dto.PatientFileDto;
import com.hospital.management.system.model.PatientFile;
import com.hospital.management.system.model.Reservation;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface PatientFileMapper {
    PatientFileMapper MAPPER = Mappers.getMapper(PatientFileMapper.class);
    @Mapping(source = "reservationId" , target = "reservations")
    PatientFile toEntity(PatientFileDto patientFileDto);

    List<PatientFile> toEntity(List<PatientFileDto> patientFileDto);
    @Mapping(target = "reservationId" , source = "reservations")
    PatientFileDto toDto(PatientFile patientFile);

    List<PatientFileDto> toDto(List<PatientFile> patientFile);
    default List<Integer> mapReservationsToIds(List<Reservation> reservations) {
        return reservations != null ? reservations.stream().map(Reservation::getId).collect(Collectors.toList()) : null;
    }

    default List<Reservation> mapIdsToReservations(List<Integer> reservationIds) {
        return reservationIds != null ? reservationIds.stream().map(id -> {
            Reservation reservation = new Reservation();
            reservation.setId(id);
            return reservation;
        }).collect(Collectors.toList()) : null;
    }
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)PatientFile partialUpdate(PatientFileDto patientFileDto, @MappingTarget PatientFile patientFile);
}
