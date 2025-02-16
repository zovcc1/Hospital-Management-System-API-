package com.hospital.management.system.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hospital.management.system.enums.ReservationStatus;
import org.checkerframework.checker.i18n.qual.Localized;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class ReservationDto {

    private Integer id;

    private String patientName;

    private String doctorName;

    private String doctorSpecialization;

    private Integer roomId;

    private Integer doctorId;

    private Integer patientFileId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime reservationDate;

    private ReservationStatus status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSpecialization() {
        return doctorSpecialization;
    }

    public void setDoctorSpecialization(String doctorSpecialization) {
        this.doctorSpecialization = doctorSpecialization;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getPatientFileId() {
        return patientFileId;
    }

    public void setPatientFileId(Integer patientFileId) {
        this.patientFileId = patientFileId;
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

    public ReservationDto(Integer id, String patientName, String doctorName, String doctorSpecialization, Integer roomId, Integer doctorId, Integer patientFileId, LocalDateTime reservationDate, ReservationStatus status) {
        this.id = id;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.doctorSpecialization = doctorSpecialization;
        this.roomId = roomId;
        this.doctorId = doctorId;
        this.patientFileId = patientFileId;
        this.reservationDate = reservationDate;
        this.status = status;
    }

    public ReservationDto() {
    }

    @Override
    public String toString() {
        return "ReservationDto{" +
                "id=" + id +
                ", patientName='" + patientName + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", doctorSpecialization='" + doctorSpecialization + '\'' +
                ", roomId=" + roomId +
                ", doctorId=" + doctorId +
                ", patientFileId=" + patientFileId +
                ", reservationDate=" + reservationDate +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReservationDto that)) return false;

        return Objects.equals(getId(), that.getId()) && Objects.equals(getPatientName(), that.getPatientName()) && Objects.equals(getDoctorName(), that.getDoctorName()) && Objects.equals(getDoctorSpecialization(), that.getDoctorSpecialization()) && Objects.equals(getRoomId(), that.getRoomId()) && Objects.equals(getDoctorId(), that.getDoctorId()) && Objects.equals(getPatientFileId(), that.getPatientFileId()) && Objects.equals(getReservationDate(), that.getReservationDate()) && Objects.equals(getStatus(), that.getStatus());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getPatientName());
        result = 31 * result + Objects.hashCode(getDoctorName());
        result = 31 * result + Objects.hashCode(getDoctorSpecialization());
        result = 31 * result + Objects.hashCode(getRoomId());
        result = 31 * result + Objects.hashCode(getDoctorId());
        result = 31 * result + Objects.hashCode(getPatientFileId());
        result = 31 * result + Objects.hashCode(getReservationDate());
        result = 31 * result + Objects.hashCode(getStatus());
        return result;
    }
}
