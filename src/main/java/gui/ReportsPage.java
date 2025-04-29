//package gui;
//
//import model.Appointment;
//import model.User;
//import presentation.AppointmentController;
//import presentation.MedicalServiceController;
//import presentation.UserController;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.util.List;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class ReportsPage extends JFrame {
//    private JPanel mainPanel;
//    private JTable table1;
//    private JFormattedTextField startTf;
//    private JFormattedTextField endTf;
//    private JButton generateButton;
//    private JScrollPane scrollPane1;
//    private JButton backButton;
//    private JTable table2;
//    private JButton exportButton;
//    private static final int WIDTH=1720;
//    private static final int HEIGHT=1020;
//
//    public ReportsPage(User user) {
//        super();
//        this.setContentPane(mainPanel);
//        this.setSize(new Dimension(WIDTH, HEIGHT));
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        this.setResizable(false);
//
//        String[] columnNames = {"Firstname", "Lastname", "Email", "Role", "Specialty", "Work schedule", "Appointments"};
//        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
//        table2.setModel(tableModel);
//        table2.setRowHeight(30);
//
//
//        fetchDoctors();
//
//        backButton.addActionListener( e -> {
//            dispose();
//            AdminDashboardPage adminDashboardPage = new AdminDashboardPage(user);
//        });
//
//        generateButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                displayAppointmentsInTable(table1,startTf.getText(),endTf.getText());
//            }
//        });
//
//        exportButton.addActionListener(e -> {
//            JFileChooser fileChooser = new JFileChooser();
//            int option = fileChooser.showSaveDialog(null);
//            if (option == JFileChooser.APPROVE_OPTION) {
//                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
//                exportTableToCSV(table1, filePath + ".csv");
//            }
//            JFileChooser fileChooser2 = new JFileChooser();
//            int option2 = fileChooser2.showSaveDialog(null);
//            if (option2 == JFileChooser.APPROVE_OPTION) {
//                String filePath = fileChooser2.getSelectedFile().getAbsolutePath();
//                exportTableToCSV(table2, filePath + ".csv");
//            }
//        });
//
//
//        this.setVisible(true);
//    }
//
//    public static void displayAppointmentsInTable(JTable table, String startDate, String endDate) {
//        List<Appointment> appointments = AppointmentController.fetchAppointmentsBetweenDates(startDate, endDate);
//
//        String[] columnNames = {"ID", "Patient", "Doctor", "Medical Service",  "Date", "Time", "Status"};
//        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
//
//        for (Appointment appointment : appointments) {
//            System.out.println(appointment);
//            User patient = UserController.handleGetUserById(appointment.getPatientId());
//            User doctor = UserController.handleGetUserById(appointment.getDoctorId());
//            String medServiceName = MedicalServiceController.fetchServiceNameById(appointment.getServiceId());
//
//            tableModel.addRow(new Object[]{
//                    appointment.getId(),
//                    patient.getLastName() + " " + patient.getFirstName(),
//                    doctor.getLastName() + " " + doctor.getFirstName(),
//                    medServiceName,
//                    appointment.getDate(),
//                    appointment.getTime(),
//                    appointment.getStatus().name()
//            });
//        }
//
//        table.setModel(tableModel);
//    }
//
//    private void fetchDoctors() {
//        UserController.handleGetAllDoctors(table2, false);
//    }
//
//    public static void exportTableToCSV(JTable table, String filePath) {
//        try {
//            DefaultTableModel model = (DefaultTableModel) table.getModel();
//            int rowCount = model.getRowCount();
//            int colCount = model.getColumnCount();
//
//            StringBuilder sb = new StringBuilder();
//
//            for (int i = 0; i < colCount; i++) {
//                sb.append(model.getColumnName(i));
//                if (i != colCount - 1) sb.append(",");
//            }
//            sb.append("\n");
//
//            for (int row = 0; row < rowCount; row++) {
//                for (int col = 0; col < colCount; col++) {
//                    Object value = model.getValueAt(row, col);
//                    sb.append(value != null ? value.toString().replace(",", " ") : "");
//                    if (col != colCount - 1) sb.append(",");
//                }
//                sb.append("\n");
//            }
//
//            java.nio.file.Files.write(java.nio.file.Paths.get(filePath), sb.toString().getBytes());
//
//            JOptionPane.showMessageDialog(null, "Export successful to: " + filePath);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Error exporting table: " + ex.getMessage());
//        }
//    }
//
//}
