package com.example.demo.presentation;

import com.example.demo.business.AppointmentService;
import com.example.demo.model.Appointment;
import com.example.demo.model.AppointmentEntry;
import com.example.demo.model.CreateAppointmentDTO;
import com.example.demo.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    private static final AppointmentService service = new AppointmentService();

    @CrossOrigin(
            origins = "http://localhost:3000",
            allowCredentials = "true",
            allowedHeaders = "*",
            methods = { RequestMethod.POST }
    )
    @PostMapping("/create")
    public static boolean handleCreateAppointment(@RequestBody CreateAppointmentDTO createAppointmentDTO) {
        Appointment appointment = new Appointment(
                createAppointmentDTO.getDate(),
                createAppointmentDTO.getTime(),
                createAppointmentDTO.getPatientId(),
                createAppointmentDTO.getMedicalServiceId(),
                createAppointmentDTO.getDoctorId()
        );
        return service.createAppointment(appointment);
    }

    @CrossOrigin(
            origins = "http://localhost:3000",
            allowCredentials = "true",
            allowedHeaders = "*",
            methods = { RequestMethod.GET }
    )
    @GetMapping("/getAll")
    public static List<Appointment> handleGetAllAppointments() {
        return service.getAllAppointments();
    }

    @CrossOrigin(
            origins = "http://localhost:3000",
            allowCredentials = "true",
            allowedHeaders = "*",
            methods = { RequestMethod.GET }
    )
    @GetMapping("/getCount")
    public static int handleGetAppointmentCountForDoctor(@RequestParam int doctorId) {
        return service.getAppointmentCountForDoctor(doctorId);
    }

    public static List<Appointment> fetchAppointmentsBetweenDates(String startDate, String endDate) {
        return service.getAppointmentsBetweenDates(startDate, endDate);
    }

    @CrossOrigin(
            origins = "http://localhost:3000",
            allowCredentials = "true",
            allowedHeaders = "*",
            methods = { RequestMethod.GET }
    )
    @GetMapping("/getByDate")
    public static List<AppointmentEntry> displayAppointmentsInTable(@RequestParam String startDate, @RequestParam String endDate) {
        List<Appointment> appointments = AppointmentController.fetchAppointmentsBetweenDates(startDate, endDate);
        List<AppointmentEntry> entries = new java.util.ArrayList<>(List.of());
        String[] columnNames = {"ID", "Patient", "Doctor", "Medical Service",  "Date", "Time", "Status"};

        for (Appointment appointment : appointments) {
            System.out.println(appointment);
            User patient = UserController.handleGetUserById(appointment.getPatientId());
            User doctor = UserController.handleGetUserById(appointment.getDoctorId());
            String medServiceName = MedicalServiceController.fetchServiceNameById(appointment.getServiceId());
            AppointmentEntry newEntry = new AppointmentEntry(
                    appointment.getId(),
                    appointment.getDate(),
                    appointment.getTime(),
                    patient.getFirstName() + " " + patient.getLastName(),
                    medServiceName,
                    doctor.getFirstName() + " " + doctor.getLastName()
            );
            entries.add(newEntry);
        }
        System.out.println(entries);

        return entries;
    }
}
