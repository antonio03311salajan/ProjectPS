package com.example.demo.model;

public class AppointmentEntry {
    private int id;
    private String date;
    private String time;
    private String patient;
    private String service;
    private String doctor;
    private StatusEnum status;

    // Constructor
    public AppointmentEntry(int id, String date, String time, String patientId, String serviceId, String doctorId) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.patient = patientId;
        this.service = serviceId;
        this.doctor = doctorId;
        this.status = StatusEnum.UPCOMING;
    }

    // Default constructor (required for serialization/deserialization)
    public AppointmentEntry() {
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AppointmentEntry{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", patient='" + patient + '\'' +
                ", service='" + service + '\'' +
                ", doctor='" + doctor + '\'' +
                ", status=" + status +
                '}';
    }
}
