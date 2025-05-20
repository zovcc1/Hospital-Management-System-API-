package com.hospital.management.system.dto;
import com.hospital.management.system.enums.Specialty;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalTime;
import java.util.Objects;

public class DoctorDto{
    private Integer id;
    private Integer departmentId;
    private String name;
    private Specialty specialty;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime shiftDateStart;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime shiftDateEnd;

    public DoctorDto(Integer id, Integer departmentId, String name, Specialty specialty, LocalTime shiftDateStart, LocalTime shiftDateEnd) {
        this.id = id;
        this.departmentId = departmentId;
        this.name = name;
        this.specialty = specialty;
        this.shiftDateStart = shiftDateStart;
        this.shiftDateEnd = shiftDateEnd;
    }

    @Override
    public String toString() {
        return "DoctorDto{" +
                "id=" + id +
                ", departmentId=" + departmentId +
                ", name='" + name + '\'' +
                ", specialty=" + specialty +
                ", shiftDateStart=" + shiftDateStart +
                ", shiftDateEnd=" + shiftDateEnd +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DoctorDto doctorDto)) return false;

        return Objects.equals(getId(), doctorDto.getId()) && Objects.equals(getDepartmentId(), doctorDto.getDepartmentId()) && Objects.equals(getName(), doctorDto.getName()) && getSpecialty() == doctorDto.getSpecialty() && Objects.equals(getShiftDateStart(), doctorDto.getShiftDateStart()) && Objects.equals(getShiftDateEnd(), doctorDto.getShiftDateEnd());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getDepartmentId());
        result = 31 * result + Objects.hashCode(getName());
        result = 31 * result + Objects.hashCode(getSpecialty());
        result = 31 * result + Objects.hashCode(getShiftDateStart());
        result = 31 * result + Objects.hashCode(getShiftDateEnd());
        return result;
    }

    public DoctorDto() {
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

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
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

