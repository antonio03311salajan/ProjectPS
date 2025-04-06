package dataAccess;

import data.DatabaseConnection;
import model.RoleEnum;
import model.SpecialtyEnum;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    public Optional<User> findByEmail(String email) {
        String query = "SELECT * FROM user WHERE email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        RoleEnum.valueOf(rs.getString("role")),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("cnp"),
                        rs.getString("phoneNumber")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return Optional.empty();
    }

    public Optional<User> findById(int id) {
        String query = "SELECT * FROM user WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        RoleEnum.valueOf(rs.getString("role")),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("cnp"),
                        rs.getString("phoneNumber")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return Optional.empty();
    }

    public boolean saveUser(User user) {
        String query = "INSERT INTO user (username, email, password, role, firstName, lastName, cnp, phoneNumber, specialty, shift) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole().name());
            stmt.setString(5, user.getFirstName());
            stmt.setString(6, user.getLastName());
            stmt.setString(7, user.getCnp());
            stmt.setString(8, user.getPhoneNumber());
            stmt.setString(9, user.getSpecialty().name());
            stmt.setInt(10, user.getShift());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return false;
    }


    public boolean updatePassword(String email, String newPassword) {
        String hashedPassword = at.favre.lib.crypto.bcrypt.BCrypt.withDefaults()
                .hashToString(12, newPassword.toCharArray());

        String query = "UPDATE user SET password = ? WHERE email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, hashedPassword);
            stmt.setString(2, email);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return false;
    }

    public boolean deleteUser(int userId) {
        String query = "DELETE FROM user WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            int affectedRows = stmt.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }

    public List<User> getAllUsers(int id) {
        List<User> users = new ArrayList<>();
        String query = "SELECT id, username, email, role, firstName, lastName, cnp, phoneNumber FROM user WHERE id <> ? AND role <> 'ADMIN'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    users.add(new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("email"),
                            "",
                            RoleEnum.valueOf(rs.getString("role")),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getString("cnp"),
                            rs.getString("phoneNumber")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    public boolean updateUser(User user) {
        String query = "UPDATE user SET username = ?, email = ?, firstName = ?, lastName = ?, phoneNumber = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

             stmt.setString(1, user.getUsername());
             stmt.setString(2, user.getEmail());
             stmt.setString(3, user.getFirstName());
             stmt.setString(4, user.getLastName());
             stmt.setString(5, user.getPhoneNumber());
             stmt.setInt(6, user.getId());

             int affectedRows = stmt.executeUpdate();
             return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return false;
    }

    public int countUsersBySpecialty(String specialty) {
        String query = "SELECT COUNT(*) FROM user WHERE specialty = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, specialty);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return 0;
    }

    public List<User> findUsersByRole(RoleEnum role) {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user WHERE role = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            String roleString = role.name();
            stmt.setString(1, roleString);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    User user = new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("password"),
                            RoleEnum.valueOf(rs.getString("role")),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getString("cnp"),
                            rs.getString("phoneNumber"),
                            rs.getString("specialty") == null ? SpecialtyEnum.ONCOLOGIST : SpecialtyEnum.valueOf(rs.getString("specialty")),
                            (rs.getObject("shift") == null ? 0 : (Integer) rs.getObject("shift"))
                    );
                    users.add(user);
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return users;
    }


}
