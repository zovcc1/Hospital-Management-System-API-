package com.hospital.management.system.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class PatientRoom {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private PatientFile patientFile;
    @JoinColumn(name = "room_id")
    @ManyToOne
    private Room room;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PatientRoom that)) return false;

        return getId().equals(that.getId()) && Objects.equals(getPatientFile(), that.getPatientFile()) && getRoom().equals(that.getRoom());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + Objects.hashCode(getPatientFile());
        result = 31 * result + getRoom().hashCode();
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public PatientRoom() {
    }

    public PatientRoom(Integer id, PatientFile patientFile, Room room) {
        this.id = id;
        this.patientFile = patientFile;
        this.room = room;
    }
}
