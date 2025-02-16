package com.hospital.management.system.repository;

import com.hospital.management.system.model.PatientRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRoomRepository extends JpaRepository<PatientRoom, Integer> {
    boolean existsByPatientFile_IdAndRoom_Id(Integer patientId, Integer roomId);

    PatientRoom findByPatientFile_IdOrRoom_Id(@Nullable Integer id, @Nullable Integer id1);
}
