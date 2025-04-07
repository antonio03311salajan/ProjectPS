package gui;

import model.Appointment;
import model.User;
import presentation.AppointmentController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportsPage extends JFrame {
    private JPanel mainPanel;
    private JTable table1;
    private JFormattedTextField startTf;
    private JFormattedTextField endTf;
    private JButton generateButton;
    private JScrollPane scrollPane1;
    private JButton backButton;
    private JTable table2;
    private JButton exportButton;
    private static final int WIDTH=1720;
    private static final int HEIGHT=1020;

    public ReportsPage(User user) {
        super();
        this.setContentPane(mainPanel);
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        backButton.addActionListener( e -> {
            dispose();
            AdminDashboardPage adminDashboardPage = new AdminDashboardPage(user);
        });

        this.setVisible(true);
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAppointmentsInTable(table1,startTf.getText(),endTf.getText());
            }
        });
    }

    public static void displayAppointmentsInTable(JTable table, String startDate, String endDate) {
        List<Appointment> appointments = AppointmentController.fetchAppointmentsBetweenDates(startDate, endDate);

        String[] columnNames = {"ID", "Doctor ID", "Patient ID", "Date", "Time"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Appointment appt : appointments) {
            Object[] row = {
                    appt.getId(),
                    appt.getDoctorId(),
                    appt.getPatientId(),
                    appt.getDate(),
                    appt.getTime()
            };
            tableModel.addRow(row);
        }

        table.setModel(tableModel);
    }
}
