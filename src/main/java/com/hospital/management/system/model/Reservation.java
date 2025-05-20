package com.hospital.management.system.model;

import java.time.LocalDateTime;
import java.util.Objects;

import com.hospital.management.system.enums.ReservationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Reservation {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Integer id;
    /*
     * each doctor can have multiple reservation (Many reservation to one Doctor)
     *
     * */
    @JoinColumn(name = "doctor_id")
    @ManyToOne
    private Doctor doctor;
    @JoinColumn(name = "patient_id")
    @ManyToOne
    private PatientFile patientFile;
    @ManyToOne(optional = true )
    @JoinColumn (name = "room_id" , nullable = true)
    private PatientRoom room;

    private LocalDateTime reservationDate;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status = ReservationStatus.PENDING;

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", doctor=" + doctor +
                ", patientFile=" + patientFile +
                ", room=" + room +
                ", reservationDate=" + reservationDate +
                ", status=" + status +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation that)) return false;

        return Objects.equals(getId(), that.getId()) && Objects.equals(getDoctor(), that.getDoctor()) && Objects.equals(getPatientFile(), that.getPatientFile()) && Objects.equals(getRoom(), that.getRoom()) && Objects.equals(getReservationDate(), that.getReservationDate()) && getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getDoctor());
        result = 31 * result + Objects.hashCode(getPatientFile());
        result = 31 * result + Objects.hashCode(getRoom());
        result = 31 * result + Objects.hashCode(getReservationDate());
        result = 31 * result + Objects.hashCode(getStatus());
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public PatientFile getPatientFile() {
        return patientFile;
    }

    public void setPatientFile(PatientFile patientFile) {
        this.patientFile = patientFile;
    }

    public PatientRoom getRoom() {
        return room;
    }

    public void setRoom(PatientRoom room) {
        this.room = room;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public Reservation(Integer id, Doctor doctor, PatientFile patientFile, PatientRoom room, LocalDateTime reservationDate, ReservationStatus status) {
        this.id = id;
        this.doctor = doctor;
        this.patientFile = patientFile;
        this.room = room;
        this.reservationDate = reservationDate;
        this.status = status;
    }

    public Reservation() {
    }
}
