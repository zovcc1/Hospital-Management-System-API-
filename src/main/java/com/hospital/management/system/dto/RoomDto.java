package com.hospital.management.system.dto;
import com.hospital.management.system.enums.RoomType;
import com.hospital.management.system.enums.Status;

import java.util.Objects;

public class RoomDto{
        Integer id;
        Integer DepartmentId;
        String departmentName;
        String departmentDescription;
        Status status ;
        RoomType type;

    @Override
    public String toString() {
        return "RoomDto{" +
                "id=" + id +
                ", DepartmentId=" + DepartmentId +
                ", departmentName='" + departmentName + '\'' +
                ", departmentDescription='" + departmentDescription + '\'' +
                ", status=" + status +
                ", type=" + type +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomDto roomDto)) return false;

        return Objects.equals(getId(), roomDto.getId()) && Objects.equals(getDepartmentId(), roomDto.getDepartmentId()) && Objects.equals(getDepartmentName(), roomDto.getDepartmentName()) && Objects.equals(getDepartmentDescription(), roomDto.getDepartmentDescription()) && getStatus() == roomDto.getStatus() && getType() == roomDto.getType();
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getDepartmentId());
        result = 31 * result + Objects.hashCode(getDepartmentName());
        result = 31 * result + Objects.hashCode(getDepartmentDescription());
        result = 31 * result + Objects.hashCode(getStatus());
        result = 31 * result + Objects.hashCode(getType());
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        DepartmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentDescription() {
        return departmentDescription;
    }

    public void setDepartmentDescription(String departmentDescription) {
        this.departmentDescription = departmentDescription;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public RoomDto(Integer id, Integer departmentId, String departmentName, String departmentDescription, Status status, RoomType type) {
        this.id = id;
        DepartmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentDescription = departmentDescription;
        this.status = status;
        this.type = type;
    }

    public RoomDto() {
    }
}
