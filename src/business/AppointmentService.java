package business;

import dataAccess.AppointmentRepo;
import model.Appointment;

import java.util.List;

public class AppointmentService {
    private final AppointmentRepo repository = new AppointmentRepo();

    public boolean createAppointment(Appointment appointment) {
        return repository.createAppointment(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return repository.getAllAppointments();
    }

    public int getAppointmentCountForDoctor(int doctorId) {
        return repository.countAppointmentsByDoctorId(doctorId);
    }

}
