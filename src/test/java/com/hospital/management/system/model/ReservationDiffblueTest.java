package com.hospital.management.system.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.hospital.management.system.enums.PatientGender;
import com.hospital.management.system.enums.ReservationStatus;
import com.hospital.management.system.enums.RoomType;
import com.hospital.management.system.enums.Specialty;
import com.hospital.management.system.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReservationDiffblueTest {
    /**
     * Test getters and setters.
     * <p>
     * Methods under test:
     * <ul>
     *   <li>{@link Reservation#setDoctor(Doctor)}
     *   <li>{@link Reservation#setId(Integer)}
     *   <li>{@link Reservation#setPatientFile(PatientFile)}
     *   <li>{@link Reservation#setReservationDate(LocalDateTime)}
     *   <li>{@link Reservation#setRoom(PatientRoom)}
     *   <li>{@link Reservation#setStatus(ReservationStatus)}
     *   <li>{@link Reservation#toString()}
     *   <li>{@link Reservation#getDoctor()}
     *   <li>{@link Reservation#getId()}
     *   <li>{@link Reservation#getPatientFile()}
     *   <li>{@link Reservation#getReservationDate()}
     *   <li>{@link Reservation#getRoom()}
     *   <li>{@link Reservation#getStatus()}
     * </ul>
     */
    @Test
    @DisplayName("Test getters and setters")
    void testGettersAndSetters() {
        // Arrange
        Reservation reservation = new Reservation();

        Department department = new Department();
        department.setDescription("The characteristics of someone or something");
        department.setId(1);
        department.setName("Name");

        Doctor doctor = new Doctor();
        doctor.setDepartment(department);
        doctor.setId(1);
        doctor.setName("Name");
        doctor.setShiftDateEnd(LocalTime.MIDNIGHT);
        doctor.setShiftDateStart(LocalTime.MIDNIGHT);
        doctor.setSpecialty(Specialty.INTERNAL_MEDICINE);

        // Act
        reservation.setDoctor(doctor);
        reservation.setId(1);
        PatientFile patientFile = new PatientFile();
        patientFile.setAddress("42 Main St");
        patientFile.setContactNumber("42");
        patientFile.setEmergencyNumber("42");
        patientFile.setGender(PatientGender.MALE);
        patientFile.setId(1);
        patientFile.setMedicalHistory("Medical History");
        patientFile.setMedications("Medications");
        patientFile.setName("Name");
        patientFile.setNotes("Notes");
        patientFile.setReservations(new ArrayList<>());
        patientFile.setTestResult("Test Result");
        reservation.setPatientFile(patientFile);
        LocalDateTime reservationDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        reservation.setReservationDate(reservationDate);
        PatientFile patientFile2 = new PatientFile();
        patientFile2.setAddress("42 Main St");
        patientFile2.setContactNumber("42");
        patientFile2.setEmergencyNumber("42");
        patientFile2.setGender(PatientGender.MALE);
        patientFile2.setId(1);
        patientFile2.setMedicalHistory("Medical History");
        patientFile2.setMedications("Medications");
        patientFile2.setName("Name");
        patientFile2.setNotes("Notes");
        patientFile2.setReservations(new ArrayList<>());
        patientFile2.setTestResult("Test Result");
        Department department2 = new Department();
        department2.setDescription("The characteristics of someone or something");
        department2.setId(1);
        department2.setName("Name");
        Room room = new Room();
        room.setDepartment(department2);
        room.setId(1);
        room.setStatus(Status.VACANT);
        room.setType(RoomType.NORMAL);
        PatientRoom room2 = new PatientRoom();
        room2.setId(1);
        room2.setPatientFile(patientFile2);
        room2.setRoom(room);
        reservation.setRoom(room2);
        reservation.setStatus(ReservationStatus.CONFIRMED);
        reservation.toString();
        Doctor actualDoctor = reservation.getDoctor();
        Integer actualId = reservation.getId();
        PatientFile actualPatientFile = reservation.getPatientFile();
        LocalDateTime actualReservationDate = reservation.getReservationDate();
        PatientRoom actualRoom = reservation.getRoom();
        ReservationStatus actualStatus = reservation.getStatus();

        // Assert that nothing has changed
        assertEquals(1, actualId.intValue());
        assertEquals(ReservationStatus.CONFIRMED, actualStatus);
        assertSame(doctor, actualDoctor);
        assertSame(patientFile, actualPatientFile);
        assertSame(room2, actualRoom);
        assertSame(reservationDate, actualReservationDate);
    }
}
