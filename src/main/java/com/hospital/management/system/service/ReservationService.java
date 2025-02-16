package com.hospital.management.system.service;

import com.hospital.management.system.dto.ReservationDto;
import com.hospital.management.system.enums.ReservationStatus;
import com.hospital.management.system.model.Reservation;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReservationService {

    ReservationDto createReservation(ReservationDto request);
    Page<ReservationDto> getAllReservations();
    Page<ReservationDto> getAllPatientReservation(Integer patientId, Pageable pageable);

    Page<ReservationDto> getAllRoomReservation(Pageable pageable);

    Page<ReservationDto> getAllDoctorReservation(String doctorName, Pageable pageable);

    ReservationDto updateReservation(ReservationDto newReservation);

    void deleteReservation(Integer reservationId); // Specify which reservation to delete

    Reservation getReservationById(Integer reservationId);

    Page<ReservationDto> getReservationsByDate(LocalDate startDate,LocalDate endDate, Pageable pageable);

    Page<ReservationDto> getPatientReservationsByDate(String patientName, LocalDate startDate, LocalDate endDate, Pageable pageable);

    Page<ReservationDto> getReservationsByPatientName(String patientName, Pageable pageable);

    Page<ReservationDto> findByStatus(ReservationStatus status, Pageable pageable);

    Page<ReservationDto> getAllReservations(Pageable pageable);

    // TODO for later versions.
    // Page<ReservationReportDto> generateReservationReport(LocalDate startDate, LocalDate endDate, Pageable pageable);
}

