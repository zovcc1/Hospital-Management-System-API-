package com.hospital.management.system.repository;

import com.hospital.management.system.model.PatientService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientServiceRepository extends JpaRepository<PatientService, Integer> {
}
