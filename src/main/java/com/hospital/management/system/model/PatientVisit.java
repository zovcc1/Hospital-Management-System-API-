package com.hospital.management.system.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class PatientVisit {
    @Id
    @GeneratedValue
    private Integer id;

    private LocalDateTime visitDate;

    private String note;
    @JoinColumn(name = "patient_file_id")
    @ManyToOne
    private PatientFile patientFile;

    @Override
    public String toString() {
        return "PatientVisit{" +
                "id=" + id +
                ", visitDate=" + visitDate +
                ", note='" + note + '\'' +
                ", patientFile=" + patientFile +
                '}';
    }


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PatientVisit that)) return false;

        return Objects.equals(getId(), that.getId()) && Objects.equals(getVisitDate(), that.getVisitDate()) && Objects.equals(getNote(), that.getNote()) && Objects.equals(getPatientFile(), that.getPatientFile());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getVisitDate());
        result = 31 * result + Objects.hashCode(getNote());
        result = 31 * result + Objects.hashCode(getPatientFile());
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDateTime visitDate) {
        this.visitDate = visitDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public PatientFile getPatientFile() {
        return patientFile;
    }

    public void setPatientFile(PatientFile patientFile) {
        this.patientFile = patientFile;
    }

    public PatientVisit() {
    }

    public PatientVisit(Integer id, LocalDateTime visitDate, String note, PatientFile patientFile) {
        this.id = id;
        this.visitDate = visitDate;
        this.note = note;
        this.patientFile = patientFile;
    }
}
