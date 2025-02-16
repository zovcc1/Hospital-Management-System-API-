package com.hospital.management.system.repository;

import com.hospital.management.system.model.PatientFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientFileRepository extends JpaRepository<PatientFile, Integer> {
}
