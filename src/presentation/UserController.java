package presentation;

import business.UserService;
import model.RoleEnum;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Optional;

public class UserController {
    private static final UserService userService = new UserService();

    public static User handleLogin(String email, String password) {
        User user = UserService.loginUser(email, password);

        if (user != null) {
            System.out.println("Welcome, " + user.getUsername() + "!");
            return user;
        } else {
            System.out.println("Login failed. Invalid email or password.");
            return null;
        }
    }


    public static boolean handleRegister(String username, String email, String password, RoleEnum role,
                                      String firstName, String lastName,
                                      String cnp, String phoneNumber) {
        boolean success = UserService.registerUser(username, email, role,
                firstName, lastName, cnp, phoneNumber);

        if (success) {
            System.out.println("User registered successfully!");
        } else {
            System.out.println("Registration failed.");
        }
        return success;
    }

    public void handleUpdatePassword(String email, String oldPassword, String newPassword) {
        boolean isUpdated = userService.updateUserPassword(email, newPassword);

        if (isUpdated) {
            System.out.println("Password updated successfully!");
        } else {
            System.out.println("Password update failed.");
        }
    }

    public static void handleDeleteUser(int userId) {
        boolean isDeleted = userService.deleteUser(userId);

        if (isDeleted) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("Error: Unable to delete user. User may not exist.");
        }
    }

    public static void refreshUserTable(JTable table, int id) {
        List<User> users = userService.fetchAllUsers(id);
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);

        for (User user : users) {
            System.out.println(user);
            tableModel.addRow(new Object[]{
                    user.getId(),
                    user.getEmail(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getRole().name()
            });
        }
    }

    public static User handleFindUserByEmail(String email) {
        Optional<User> userOptional = UserService.findUserByEmail(email);

        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            System.out.println("User not found with email: " + email);
            return null;
        }
    }

    public static boolean handleUpdateUser(int userId, String username, String email, String firstName, String lastName, String phoneNumber) {
        return userService.updateUserInfo(userId, username, email, firstName, lastName, phoneNumber);
    }

    public static int handleGetUserCountBySpecialty(String specialty) {
        return userService.getUserCountBySpecialty(specialty);
    }

    public static void handleGetAllDoctors(JTable table) {
        List<User> users = UserService.getAllByRole(RoleEnum.DOCTOR);

        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);

        for (User user : users) {
            System.out.println(user);
            tableModel.addRow(new Object[]{
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getRole().name(),
                    user.getSpecialty().name(),
                    user.getShift(),
                    0
            });
        }
    }
}
