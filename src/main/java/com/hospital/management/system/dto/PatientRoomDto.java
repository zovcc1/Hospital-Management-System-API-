package com.hospital.management.system.dto;

import java.util.Objects;

public class PatientRoomDto {


    private Integer id;
    private Integer patientFileId;
    private Integer roomId;

    public PatientRoomDto(Integer id, Integer patientFileId, Integer roomId) {
        this.id = id;
        this.patientFileId = patientFileId;
        this.roomId = roomId;
    }

    public PatientRoomDto() {
    }

    @Override
    public String toString() {
        return "PatientRoomDto{" +
                "id=" + id +
                ", patientFile=" + patientFileId +
                ", roomId=" + roomId +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PatientRoomDto that)) return false;

        return Objects.equals(getId(), that.getId()) && Objects.equals(getPatientFileId(), that.getPatientFileId()) && Objects.equals(getRoomId(), that.getRoomId());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getPatientFileId());
        result = 31 * result + Objects.hashCode(getRoomId());
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatientFileId() {
        return patientFileId;
    }

    public void setPatientFileId(Integer patientFileId) {
        this.patientFileId = patientFileId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
}
