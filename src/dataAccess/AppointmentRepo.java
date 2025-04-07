package dataAccess;

import data.DatabaseConnection;
import model.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepo {public boolean createAppointment(Appointment appointment) {
    String sql = "INSERT INTO appointment (patientId, service, date, time, doctorId, status) VALUES (?, ?, ?, ?, ?, ?)";
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {

        stmt.setInt(1, appointment.getPatientId());
        stmt.setInt(2, appointment.getServiceId());
        stmt.setString(3, appointment.getDate());
        stmt.setString(4, appointment.getTime());
        stmt.setInt(5, appointment.getDoctorId());
        stmt.setString(6, appointment.getStatus().name());

        int affectedRows = stmt.executeUpdate();
        return affectedRows > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointment";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Appointment appointment = new Appointment(
                        rs.getInt("id"),
                        rs.getString("date"),
                        rs.getString("time"),
                        rs.getInt("patientId"),
                        rs.getInt("service"),
                        rs.getInt("doctorId")
                );
                appointments.add(appointment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }

    public int countAppointmentsByDoctorId(int doctorId) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM appointment WHERE doctorId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, doctorId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    public List<Appointment> findAppointmentsBetweenDates(String startDate, String endDate) {
        List<Appointment> appointments = new ArrayList<>();
        DateTimeFormatter dbFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            LocalDate start = LocalDate.parse(startDate, dbFormat);
            LocalDate end = LocalDate.parse(endDate, dbFormat);

            String query = "SELECT * FROM appointment";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    String dateStr = rs.getString("date");
                    LocalDate appointmentDate = LocalDate.parse(dateStr, dbFormat);

                    if ((appointmentDate.isEqual(start) || appointmentDate.isAfter(start)) &&
                            (appointmentDate.isEqual(end) || appointmentDate.isBefore(end))) {

                        appointments.add(new Appointment(
                                rs.getString("date"),
                                rs.getString("time"),
                                rs.getInt("patientId"),
                                rs.getInt("doctorId"),
                                rs.getInt("service")
                        ));
                    }
                }

            } catch (SQLException | DateTimeParseException e) {
                System.out.println("Error while querying DB: " + e.getMessage());
            }

        } catch (DateTimeParseException e) {
            System.out.println("Invalid date input format. Use dd/MM/yyyy");
        }

        return appointments;
    }
}
