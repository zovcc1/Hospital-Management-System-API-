package com.hospital.management.system.repository;

import com.hospital.management.system.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    @Query("SELECT d FROM Doctor d WHERE :currentTime BETWEEN d.shiftDateStart AND d.shiftDateEnd")
    List<Doctor> findAvailableDoctors(@Param("currentTime") LocalTime currentTime);

}
