package model;

public class Appointment {
    private int id;
    private String date;
    private String time;
    private int patientId;
    private int serviceId;
    private int doctorId;
    private StatusEnum status;

    public Appointment(int id, String date, String time, int patientId, int serviceId, int doctorId) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.patientId = patientId;
        this.serviceId = serviceId;
        this.doctorId = doctorId;
        this.status = StatusEnum.UPCOMING;
    }

    public Appointment(String date, String time, int patientId, int serviceId, int doctorId) {
        this.date = date;
        this.time = time;
        this.patientId = patientId;
        this.serviceId = serviceId;
        this.doctorId = doctorId;
        this.status = StatusEnum.UPCOMING;
    }

    public int getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public StatusEnum getStatus() {
        return status;
    }
    public void setStatus(StatusEnum status) {
        this.status = status;
    }

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

    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getServiceId() {
        return serviceId;
    }
    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
}
