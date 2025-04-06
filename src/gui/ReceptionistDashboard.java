package gui;

import model.User;
import presentation.UserController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReceptionistDashboard extends JFrame {
    private JPanel mainPanel;
    private JTable patientsTable;
    private JTable doctorsTable;
    private JTable serviceTable;
    private JButton validateButton;
    private JButton logOutButton;
    private JPanel patientsPanel;
    private JPanel doctorsPanel;
    private JTable appointmentsTable;
    private JPanel appointmentsPanel;
    private JPanel servicePanel;
    private JScrollPane scrollPane1;
    private JScrollPane scrollPanel2;

    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;

    public ReceptionistDashboard(User user) {
        super();
        this.setContentPane(mainPanel);
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        //doctors table and data
        String[] columnNamesDoctor = {"Id", "Firstname", "Lastname", "Email", "Role", "Specialty", "Work schedule"};
        DefaultTableModel tableModel1 = new DefaultTableModel(columnNamesDoctor, 0);
        doctorsTable = new JTable(tableModel1);
        doctorsTable.setRowHeight(30);

        scrollPane1 = new JScrollPane(doctorsTable);
        doctorsPanel.setLayout(new BorderLayout());
        doctorsPanel.add(scrollPane1, BorderLayout.CENTER);

        fetchDoctorsData();

        //users table and data
        String[] columnNamesUser = {"Id", "Firstname", "Lastname", "Email"};
        DefaultTableModel tableModel2 = new DefaultTableModel(columnNamesUser, 0);
        patientsTable = new JTable(tableModel2);
        patientsTable.setRowHeight(30);

        scrollPanel2 = new JScrollPane(patientsTable);
        patientsPanel.setLayout(new BorderLayout());
        patientsPanel.add(scrollPanel2, BorderLayout.CENTER);

        fetchPatientsData();

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginPage loginPage = new LoginPage();
            }
        });

        this.setVisible(true);
    }

    private void fetchDoctorsData(){
        UserController.handleGetAllDoctors(doctorsTable, true);
    }

    private void fetchPatientsData(){
        UserController.handleGetAllPatients(patientsTable);
    }
}
