package dataAccess;

import data.DatabaseConnection;
import model.MedicalService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicalServiceRepository {

    public void createMedicalService(MedicalService service) {
        String query = "INSERT INTO service (name, price, duration) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, service.getName());
            stmt.setFloat(2, service.getPrice());
            stmt.setInt(3, service.getDuration());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error creating medical service: " + e.getMessage());
        }
    }

    public List<MedicalService> getAllMedicalServices() {
        List<MedicalService> services = new ArrayList<>();
        String query = "SELECT * FROM service";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MedicalService service = new MedicalService(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getInt("duration")
                );
                services.add(service);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching medical services: " + e.getMessage());
        }

        return services;
    }
}
