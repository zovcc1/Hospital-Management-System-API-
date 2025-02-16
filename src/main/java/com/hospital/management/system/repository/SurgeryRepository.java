package com.hospital.management.system.repository;

import com.hospital.management.system.model.Surgery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurgeryRepository extends JpaRepository<Surgery, Integer> {
}
