package com.example.demo.dataAccess;

import com.example.demo.data.DatabaseConnection;
import com.example.demo.model.MedicalService;

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

    public boolean deleteById(int id) {
        String sql = "DELETE FROM service WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getServiceNameById(int id) {
        String query = "SELECT name FROM service WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("name");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching service name: " + e.getMessage());
        }
        return null;
    }
}
