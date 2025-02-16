package com.hospital.management.system.repository;

import com.hospital.management.system.enums.ReservationStatus;
import com.hospital.management.system.model.PatientRoom;
import com.hospital.management.system.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    boolean existsByReservationDate(LocalDateTime reservationDate);
    Page<Reservation> findByStatus(ReservationStatus status,Pageable pageable);
    boolean existsByRoom(@NonNull PatientRoom room);
    Page<Reservation> findByRoomIsNotNull(Pageable pageable);
    @Query("SELECT r FROM Doctor r " +
            " where upper(r.name) like UPPER(concat('%' ,:search, '%') ) ")
    Page<Reservation> findByDoctor_Name(@Param("search") String name, Pageable pageable);
    Page<Reservation> findByPatientFile_NameContainsAndReservationDateBetween(String patientName, LocalDate startDate, LocalDate endDate, Pageable pageable);

    Page<Reservation> findByPatientFile_NameContains(String name, Pageable pageable );

    Page<Reservation> findByPatientFile_Id(Integer id, Pageable pageable);
    Page<Reservation> findAll(Pageable pageable);
    Page<Reservation> findByReservationDateBetween(LocalDate reservationDateStart, LocalDate reservationDateEnd, Pageable pageable);

    List<Reservation> findByRoomAndReservationDate(PatientRoom room, LocalDateTime reservationDate);

    List<Reservation> findByRoom_IdAndReservationDate(Integer id, LocalDateTime reservationDate);
}
