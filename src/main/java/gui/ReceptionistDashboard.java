//package gui;
//
//import model.Appointment;
//import model.MedicalService;
//import model.User;
//import presentation.AppointmentController;
//import presentation.MedicalServiceController;
//import presentation.UserController;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableModel;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeParseException;
//
//public class ReceptionistDashboard extends JFrame {
//    private JPanel mainPanel;
//    private JTable patientsTable;
//    private JTable doctorsTable;
//    private JTable serviceTable;
//    private JButton validateButton;
//    private JButton logOutButton;
//    private JPanel patientsPanel;
//    private JPanel doctorsPanel;
//    private JTable appointmentsTable;
//    private JPanel appointmentsPanel;
//    private JPanel servicePanel;
//    private JScrollPane scrollPane1;
//    private JScrollPane scrollPanel2;
//    private JScrollPane scrollPane3;
//    private JScrollPane scrollPane4;
//    private JFormattedTextField ddMmYyFormattedTextField;
//    private JComboBox comboBox1;
//
//    public static final int WIDTH = 1720;
//    public static final int HEIGHT = 1080;
//
//    public ReceptionistDashboard(User user) {
//        super();
//        this.setContentPane(mainPanel);
////        this.setSize(new Dimension(WIDTH, HEIGHT));
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        this.setResizable(false);
//
//        //doctors table and data
//        String[] columnNamesDoctor = {"Id", "Firstname", "Lastname", "Email", "Role", "Specialty", "Work schedule"};
//        DefaultTableModel tableModel1 = new DefaultTableModel(columnNamesDoctor, 0);
//        doctorsTable = new JTable(tableModel1);
//        doctorsTable.setRowHeight(30);
//
//        scrollPane1 = new JScrollPane(doctorsTable);
//        doctorsPanel.setLayout(new BorderLayout());
//        doctorsPanel.add(scrollPane1, BorderLayout.CENTER);
//
//        fetchDoctorsData();
//
//        //users table and data
//        String[] columnNamesUser = {"Id", "Firstname", "Lastname", "Email"};
//        DefaultTableModel tableModel2 = new DefaultTableModel(columnNamesUser, 0);
//        patientsTable = new JTable(tableModel2);
//        patientsTable.setRowHeight(30);
//
//        scrollPanel2 = new JScrollPane(patientsTable);
//        patientsPanel.setLayout(new BorderLayout());
//        patientsPanel.add(scrollPanel2, BorderLayout.CENTER);
//
//        fetchPatientsData();
//
//        //medical services and data
//        String[] columnNamesMedicalService = {"Id", "Name", "Price", "Duration"};
//        DefaultTableModel serviceModel = new DefaultTableModel(columnNamesMedicalService, 0);
//        serviceTable = new JTable(serviceModel);
//        serviceTable.setRowHeight(30);
//
//        scrollPane3 = new JScrollPane(serviceTable);
//        servicePanel.setLayout(new BorderLayout());
//        servicePanel.add(scrollPane3, BorderLayout.CENTER);
//
//        refreshMedicalServices(serviceTable);
//
//        //appointments table and data
//        String[] columnNamesAppointments = {"Id", "Patient Name", "Doctor Name", "Service Name", "Date", "Time", "Status"};
//        DefaultTableModel appointmentsModel = new DefaultTableModel(columnNamesAppointments, 0);
//        appointmentsTable = new JTable(appointmentsModel);
//        appointmentsTable.setRowHeight(30);
//
//        scrollPane4 = new JScrollPane(appointmentsTable);
//        appointmentsPanel.setLayout(new BorderLayout());
//        appointmentsPanel.add(scrollPane4, BorderLayout.CENTER);
//
//        refreshAppointments(appointmentsTable);
//
//        logOutButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                dispose();
//                LoginPage loginPage = new LoginPage();
//            }
//        });
//
//
//        this.setVisible(true);
//        validateButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int selectedRow1 = patientsTable.getSelectedRow();
//                int selectedRow2 = doctorsTable.getSelectedRow();
//                int selectedRow3 = serviceTable.getSelectedRow();
//                TableModel patientsModel = patientsTable.getModel();
//                TableModel doctorsModel = doctorsTable.getModel();
//                TableModel serviceModel = serviceTable.getModel();
//
//                if (selectedRow1 != -1 && selectedRow2 != -1 && selectedRow3 != -1) {
//                    String date = ddMmYyFormattedTextField.getText();
//                    String datePattern = "^([0][1-9]|[12][0-9]|3[01])/([0][1-9]|1[0-2])/\\d{4}$";
//
//                    if (!date.matches(datePattern)) {
//                        JOptionPane.showMessageDialog(null, "Invalid date format. Please use dd/mm/yyyy.", "Input Error", JOptionPane.ERROR_MESSAGE);
//                        return;
//                    }
//
//                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//                    try {
//                        LocalDate inputDate = LocalDate.parse(date, formatter);
//                        LocalDate today = LocalDate.now();
//
//                        if (!inputDate.isAfter(today)) {
//                            JOptionPane.showMessageDialog(null, "The selected date must be in the future.", "Validation Error", JOptionPane.WARNING_MESSAGE);
//                            return;
//                        }
//
//                        String hour = comboBox1.getSelectedItem().toString();
//                        int patientId = Integer.parseInt(patientsModel.getValueAt(selectedRow1,0).toString());
//                        int doctorId = Integer.parseInt(doctorsModel.getValueAt(selectedRow2,0).toString());
//                        int serviceId = Integer.parseInt(serviceModel.getValueAt(selectedRow3,0).toString());
//
//                        addNewAppointment(patientId, serviceId, date, hour, doctorId);
//                        refreshAppointments(appointmentsTable);
//
//                    } catch (DateTimeParseException ex) {
//                        JOptionPane.showMessageDialog(null, "Invalid date value.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
//                    }
//                }
//            }
//        });
//    }
//
//    private void fetchDoctorsData(){
//        UserController.handleGetAllDoctors(doctorsTable, true);
//    }
//
//    private void fetchPatientsData(){
//        UserController.handleGetAllPatients(patientsTable);
//    }
//
//    public void refreshMedicalServices(JTable serviceTable) {
//        DefaultTableModel model = (DefaultTableModel) serviceTable.getModel();
//        model.setRowCount(0);
//
//        for (MedicalService service : MedicalServiceController.handleGetAllMedicalServices()) {
//            model.addRow(new Object[]{
//                    service.getId(),
//                    service.getName(),
//                    service.getPrice(),
//                    service.getDuration()
//            });
//        }
//    }
//
//    public void addNewAppointment(int patientId, int medicalServiceId, String date, String time, int doctorId){
//        AppointmentController.handleCreateAppointment(patientId, medicalServiceId, date, time, doctorId);
//    }
//
//    public void refreshAppointments(JTable appointmentsTable) {
//        DefaultTableModel model = (DefaultTableModel) appointmentsTable.getModel();
//        model.setRowCount(0);
//
//
//        for (Appointment appointment : AppointmentController.handleGetAllAppointments()) {
//            User patient = UserController.handleGetUserById(appointment.getPatientId());
//            User doctor = UserController.handleGetUserById(appointment.getDoctorId());
//            String medServiceName = MedicalServiceController.fetchServiceNameById(appointment.getServiceId());
//
//            model.addRow(new Object[]{
//                    appointment.getId(),
//                    patient.getLastName() + " " + patient.getFirstName(),
//                    doctor.getLastName() + " " + doctor.getFirstName(),
//                    medServiceName,
//                    appointment.getDate(),
//                    appointment.getTime(),
//                    appointment.getStatus().name()
//            });
//        }
//    }
//}
