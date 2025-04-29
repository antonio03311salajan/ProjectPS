//package gui;
//
//import model.User;
//import presentation.UserController;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableModel;
//import java.awt.*;
//
//public class AdminDashboardPage extends JFrame {
//    private JPanel mainPanel;
//    private JTable table1;
//    private JButton doctorsButton;
//    private JButton receptionistsButton;
//    private JButton patientsButton;
//    private JButton medicalServicesButton;
//    private  JButton logoutButton;
//    private JButton reportsButton;
//    private static final int WIDTH = 1720;
//    private static final int HEIGHT = 1080;
//
//    public AdminDashboardPage(User user) {
//        super("Admin Dashboard");
//        setSize(WIDTH, HEIGHT);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setResizable(false);
//
//        mainPanel = new JPanel(new BorderLayout());
//
//        JPanel sidePanel = new JPanel();
//        sidePanel.setLayout(new GridBagLayout());
//        sidePanel.setPreferredSize(new Dimension(200, HEIGHT));
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.insets = new Insets(10, 10, 10, 10);
//        gbc.gridx = 0;
//
//        doctorsButton = new JButton("Doctors");
//        receptionistsButton = new JButton("Receptionists");
//        patientsButton = new JButton("Patients");
//        medicalServicesButton = new JButton("Manage Medical Services");
//        logoutButton = new JButton("Logout");
//        reportsButton = new JButton("Reports");
//
//        logoutButton.setPreferredSize(new Dimension(100, 30));
//        logoutButton.addActionListener(e -> {
//            dispose();
//            new LoginPage();
//        });
//
//        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        topPanel.add(logoutButton);
//        mainPanel.add(topPanel, BorderLayout.NORTH);
//
//        Dimension buttonSize = new Dimension(180, 40);
//        doctorsButton.setPreferredSize(buttonSize);
//        receptionistsButton.setPreferredSize(buttonSize);
//        patientsButton.setPreferredSize(buttonSize);
//        medicalServicesButton.setPreferredSize(buttonSize);
//        reportsButton.setPreferredSize(buttonSize);
//
//        gbc.gridy = 0;
//        sidePanel.add(doctorsButton, gbc);
//        gbc.gridy = 1;
//        sidePanel.add(reportsButton, gbc);
////        sidePanel.add(receptionistsButton, gbc);
////        gbc.gridy = 2;
////        sidePanel.add(patientsButton, gbc);
//        gbc.gridy = 3;
//        sidePanel.add(medicalServicesButton, gbc);
//
//        JPanel tablePanel = new JPanel(new BorderLayout());
//        JLabel welcomeLabel = new JLabel("Welcome back!", SwingConstants.CENTER);
//        tablePanel.add(welcomeLabel, BorderLayout.NORTH);
//
//        String[] columnNames = {"ID", "Email", "Firstname", "Lastname", "Role"};
//        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
//        table1 = new JTable(tableModel);
//        table1.setRowHeight(30);
//        JScrollPane scrollPane = new JScrollPane(table1);
//
//        tablePanel.add(scrollPane, BorderLayout.CENTER);
//
//        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
//        JButton addButton = new JButton("Add");
//        JButton editButton = new JButton("Edit");
//        JButton deleteButton = new JButton("Delete");
//        JButton refreshButton = new JButton("Refresh");
//
//        addButton.setPreferredSize(new Dimension(100, 40));
//        editButton.setPreferredSize(new Dimension(100, 40));
//        deleteButton.setPreferredSize(new Dimension(100, 40));
//        refreshButton.setPreferredSize(new Dimension(100, 40));
//
//        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
//
//        buttonPanel.add(addButton);
//        buttonPanel.add(editButton);
//        buttonPanel.add(deleteButton);
//        buttonPanel.add(refreshButton);
//
//        tablePanel.add(buttonPanel, BorderLayout.SOUTH);
//
//        mainPanel.add(sidePanel, BorderLayout.WEST);
//        mainPanel.add(tablePanel, BorderLayout.CENTER);
//
//        add(mainPanel);
//        UserController.refreshUserTable(table1, user.getId());
//
//        refreshButton.addActionListener(e -> UserController.refreshUserTable(table1, user.getId()));
//
//        addButton.addActionListener(e -> {
//            dispose();
//            new CreateUserPage(user);
//        });
//
//        deleteButton.addActionListener(e -> {
//            int selectedRow = table1.getSelectedRow();
//            TableModel model = table1.getModel();
//            if (selectedRow != -1){
//                int id = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
//                UserController.handleDeleteUser(id);
//            }
//            UserController.refreshUserTable(table1, user.getId());
//        });
//
//        editButton.addActionListener(e -> {
//            int selectedRow = table1.getSelectedRow();
//            TableModel model = table1.getModel();
//            if (selectedRow != -1){
//                dispose();
//                String email = model.getValueAt(selectedRow, 1).toString();
//                User foundUser = UserController.handleFindUserByEmail(email);
//                new UpdateUserPage(user, foundUser);
//            }
//        });
//
//        doctorsButton.addActionListener(e -> {
//            dispose();
//            DoctorsStatistics doctorsStats = new DoctorsStatistics(user);
//        });
//
//        medicalServicesButton.addActionListener(e -> {
//            dispose();
//            MedicalServicesPage medicalServicesPage = new MedicalServicesPage(user);
//        });
//
//        reportsButton.addActionListener( e -> {
//            dispose();
//            ReportsPage reportsPage = new ReportsPage(user);
//        });
//
//        setVisible(true);
//    }
//}
