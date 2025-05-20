package com.hospital.management.system.repository;

import com.hospital.management.system.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    @Query("select d from Doctor d where NOT EXISTS " +
            "(select r from Reservation r where r.doctor = d AND :currentTime BETWEEN d.shiftDateStart AND d.shiftDateEnd)")
    List<Doctor> findAvailableDoctors(@Param("currentTime") LocalTime currentTime);


}
