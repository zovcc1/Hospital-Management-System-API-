package com.hospital.management.system.model;

import com.hospital.management.system.enums.Specialty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import org.checkerframework.checker.i18n.qual.Localized;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="dep_id")
    private Department department;
    private String name;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @DateTimeFormat(iso= DateTimeFormat.ISO.TIME)
    private LocalTime shiftDateStart;
    @DateTimeFormat(iso= DateTimeFormat.ISO.TIME)
    private LocalTime shiftDateEnd;

    public Doctor() {
    }


    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public Doctor(Integer id, Department department, String name, Specialty specialty, LocalTime shiftDateStart, LocalTime shiftDateEnd) {
        this.id = id;
        this.department = department;
        this.name = name;
        this.specialty = specialty;
        this.shiftDateStart = shiftDateStart;
        this.shiftDateEnd = shiftDateEnd;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", department=" + department +
                ", name='" + name + '\'' +
                ", specialty=" + specialty +
                ", shiftDateStart=" + shiftDateStart +
                ", shiftDateEnd=" + shiftDateEnd +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Doctor doctor)) return false;

        return Objects.equals(getId(), doctor.getId()) && Objects.equals(getDepartment(), doctor.getDepartment()) && Objects.equals(getName(), doctor.getName()) && getSpecialty() == doctor.getSpecialty() && Objects.equals(getShiftDateStart(), doctor.getShiftDateStart()) && Objects.equals(getShiftDateEnd(), doctor.getShiftDateEnd());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getDepartment());
        result = 31 * result + Objects.hashCode(getName());
        result = 31 * result + Objects.hashCode(getSpecialty());
        result = 31 * result + Objects.hashCode(getShiftDateStart());
        result = 31 * result + Objects.hashCode(getShiftDateEnd());
        return result;
    }

    public LocalTime getShiftDateStart() {
        return shiftDateStart;
    }

    public void setShiftDateStart(LocalTime shiftDateStart) {
        this.shiftDateStart = shiftDateStart;
    }

    public LocalTime getShiftDateEnd() {
        return shiftDateEnd;
    }

    public void setShiftDateEnd(LocalTime shiftDateEnd) {
        this.shiftDateEnd = shiftDateEnd;
    }
}
