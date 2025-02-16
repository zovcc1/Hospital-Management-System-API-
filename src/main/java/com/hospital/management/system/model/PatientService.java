package com.hospital.management.system.model;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class PatientService {
    @Id
    @GeneratedValue
    private Integer id;

    @JoinColumn(name = "patient_file_id")
    @ManyToOne
    private PatientFile patientFile;

    @JoinColumn(name = "service_id")
    @ManyToOne
    private Service service;

    public PatientService(Integer id, PatientFile patientFile, Service service) {
        this.id = id;
        this.patientFile = patientFile;
        this.service = service;
    }

    @Override
    public String toString() {
        return "PatientService{" +
                "id=" + id +
                ", patientFile=" + patientFile +
                ", service=" + service +
                '}';
    }


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PatientService that)) return false;

        return Objects.equals(getId(), that.getId()) && Objects.equals(getPatientFile(), that.getPatientFile()) && Objects.equals(getService(), that.getService());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getPatientFile());
        result = 31 * result + Objects.hashCode(getService());
        return result;
    }

    public PatientService() {
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

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
