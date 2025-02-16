package com.hospital.management.system.repository;

import com.hospital.management.system.model.PatientVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientVisitRepository extends JpaRepository<PatientVisit, Integer> {
}
