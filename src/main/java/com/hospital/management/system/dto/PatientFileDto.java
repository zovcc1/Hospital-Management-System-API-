package com.hospital.management.system.dto;

import com.hospital.management.system.enums.PatientGender;

import java.util.List;
import java.util.Objects;

public class PatientFileDto {

    private Integer id;
    private String name;
    private PatientGender gender;
    private String address;
    private String notes;
    private String contactNumber;
    private String emergencyNumber;
    private String medicalHistory;
    private String testResult;
    private String medications;
    private List<Integer> reservationId;

    public PatientFileDto(Integer id, String name, PatientGender gender, String address, String notes, String contactNumber, String emergencyNumber, String medicalHistory, String testResult, String medications, List<Integer> reservationId) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.notes = notes;
        this.contactNumber = contactNumber;
        this.emergencyNumber = emergencyNumber;
        this.medicalHistory = medicalHistory;
        this.testResult = testResult;
        this.medications = medications;
        this.reservationId = reservationId;
    }

    @Override
    public String toString() {
        return "PatientFileDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", notes='" + notes + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", emergencyNumber='" + emergencyNumber + '\'' +
                ", medicalHistory='" + medicalHistory + '\'' +
                ", testResult='" + testResult + '\'' +
                ", medications='" + medications + '\'' +
                ", reservationId=" + reservationId +
                '}';
    }

    @Override
    public final boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof PatientFileDto that)) return false;

        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && getGender() == that.getGender() && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getNotes(), that.getNotes()) && Objects.equals(getContactNumber(), that.getContactNumber()) && Objects.equals(getEmergencyNumber(), that.getEmergencyNumber()) && Objects.equals(getMedicalHistory(), that.getMedicalHistory()) && Objects.equals(getTestResult(), that.getTestResult()) && Objects.equals(getMedications(), that.getMedications()) && Objects.equals(getReservationId(), that.getReservationId());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getName());
        result = 31 * result + Objects.hashCode(getGender());
        result = 31 * result + Objects.hashCode(getAddress());
        result = 31 * result + Objects.hashCode(getNotes());
        result = 31 * result + Objects.hashCode(getContactNumber());
        result = 31 * result + Objects.hashCode(getEmergencyNumber());
        result = 31 * result + Objects.hashCode(getMedicalHistory());
        result = 31 * result + Objects.hashCode(getTestResult());
        result = 31 * result + Objects.hashCode(getMedications());
        result = 31 * result + Objects.hashCode(getReservationId());
        return result;
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

    public PatientGender getGender() {
        return gender;
    }

    public void setGender(PatientGender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmergencyNumber() {
        return emergencyNumber;
    }

    public void setEmergencyNumber(String emergencyNumber) {
        this.emergencyNumber = emergencyNumber;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public List<Integer> getReservationId() {
        return reservationId;
    }

    public void setReservationId(List<Integer> reservationId) {
        this.reservationId = reservationId;
    }
}
