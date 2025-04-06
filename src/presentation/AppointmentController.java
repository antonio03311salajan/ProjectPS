package presentation;

import business.AppointmentService;
import model.Appointment;

import java.util.List;

public class AppointmentController {
    private static final AppointmentService service = new AppointmentService();

    public static boolean handleCreateAppointment(int patientId, int medicalServiceId, String date, String time, int doctorId) {
        Appointment appointment = new Appointment(date, time, patientId, medicalServiceId, doctorId);
        return service.createAppointment(appointment);
    }

    public static List<Appointment> handleGetAllAppointments() {
        return service.getAllAppointments();
    }

    public static int handleGetAppointmentCountForDoctor(int doctorId) {
        return service.getAppointmentCountForDoctor(doctorId);
    }
}
