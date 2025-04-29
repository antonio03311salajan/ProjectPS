package com.example.demo.model;


public class CreateAppointmentDTO {
    private int patientId;
    private int medicalServiceId;
    private String date;
    private String time;
    private int doctorId;

    // Constructors
    public CreateAppointmentDTO() {}

    public CreateAppointmentDTO(int patientId, int medicalServiceId, String date, String time, int doctorId) {
        this.patientId = patientId;
        this.medicalServiceId = medicalServiceId;
        this.date = date;
        this.time = time;
        this.doctorId = doctorId;
    }

    // Getters and Setters
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getMedicalServiceId() {
        return medicalServiceId;
    }

    public void setMedicalServiceId(int medicalServiceId) {
        this.medicalServiceId = medicalServiceId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
}
