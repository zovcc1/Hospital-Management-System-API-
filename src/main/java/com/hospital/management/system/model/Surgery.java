package com.hospital.management.system.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
public class Surgery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @JoinColumn(name = "patient_id")
    @ManyToOne
    private PatientFile patientFile;
    @JoinColumn(name = "doctor_id")
    @OneToMany
    private Set<Doctor> doctors;

    @JoinColumn(name = "room_id")
    @ManyToOne
    private Room room;

    private LocalDateTime reservationDate;

    public Surgery(Integer id, PatientFile patientFile, Set<Doctor> doctors, Room room, LocalDateTime reservationDate) {
        this.id = id;
        this.patientFile = patientFile;
        this.doctors = doctors;
        this.room = room;
        this.reservationDate = reservationDate;
    }

    public Surgery() {

    }

    @Override
    public String toString() {
        return "Surgery{" +
                "id=" + id +
                ", patientFile=" + patientFile +
                ", doctors=" + doctors +
                ", room=" + room +
                ", reservationDate=" + reservationDate +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Surgery surgery)) return false;

        return Objects.equals(getId(), surgery.getId()) && Objects.equals(getPatientFile(), surgery.getPatientFile()) && Objects.equals(getDoctors(), surgery.getDoctors()) && Objects.equals(getRoom(), surgery.getRoom()) && Objects.equals(getReservationDate(), surgery.getReservationDate());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getPatientFile());
        result = 31 * result + Objects.hashCode(getDoctors());
        result = 31 * result + Objects.hashCode(getRoom());
        result = 31 * result + Objects.hashCode(getReservationDate());
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PatientFile getPatientFile() {
        return patientFile;
    }

    public void setPatientFile(PatientFile patientFile) {
        this.patientFile = patientFile;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }
}
