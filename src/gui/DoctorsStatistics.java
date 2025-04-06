package gui;

import model.SpecialtyEnum;
import model.User;
import presentation.UserController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DoctorsStatistics extends JFrame {
    private JPanel mainP;
    private JLabel cardiologistNumber;
    private JLabel oncologistNumber;
    private JLabel surgeonNumber;
    private JLabel pathologistNumber;
    private JLabel psychiatristNumber;
    private JTable table1;
    private JButton backButton;
    private JPanel tablePanel;
    private JScrollPane scrollPane;
    private JTable table2;

    private static final int WIDTH = 1720;
    private static final int HEIGHT = 1080;

    public DoctorsStatistics(User user) {
        super();
        this.setContentPane(mainP);
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        String[] columnNames = {"Firstname", "Lastname", "Email", "Role", "Specialty", "Work schedule", "Appointments"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        table1 = new JTable(tableModel);
        table1.setRowHeight(30);

        scrollPane = new JScrollPane(table1);
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        refreshStatisticsData();
        fetchDoctors();

        this.backButton.addActionListener(e -> {
            dispose();
            AdminDashboardPage adminDashboardPage = new AdminDashboardPage(user);
        });

        this.setVisible(true);
    }

    private void refreshStatisticsData() {
        this.cardiologistNumber.setText(String.valueOf(UserController.handleGetUserCountBySpecialty("CARDIOLOGIST")));
        this.oncologistNumber.setText(String.valueOf(UserController.handleGetUserCountBySpecialty("ONCOLOGIST")));
        this.surgeonNumber.setText(String.valueOf(UserController.handleGetUserCountBySpecialty("SURGEON")));
        this.pathologistNumber.setText(String.valueOf(UserController.handleGetUserCountBySpecialty("PATHOLOGIST")));
        this.psychiatristNumber.setText(String.valueOf(UserController.handleGetUserCountBySpecialty("PSYCHOLOGIST")));
    }

    private void fetchDoctors() {
        UserController.handleGetAllDoctors(table1, false);
    }

}
