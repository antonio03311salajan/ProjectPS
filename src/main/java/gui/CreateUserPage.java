//package gui;
//
//import model.RoleEnum;
//import model.SpecialtyEnum;
//import model.User;
//import presentation.UserController;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class CreateUserPage extends JFrame {
//    private JPanel mainPanel;
//    private JTextField usernameTf;
//    private JTextField emailTf;
//    private JTextField firstNameTf;
//    private JTextField firstNameTf;
//    private JTextField lastNameTf;
//    private JTextField cnpTf;
//    private JTextField phoneNoTf;
//    private JComboBox<RoleEnum> roleC;
//    private JButton addUserButton;
//    private JButton backButton;
//    private JComboBox scheduleComboBox;
//    private JComboBox<SpecialtyEnum> specialtyCombobox;
//
//    private static final int WIDTH = 1720;
//    private static final int HEIGHT = 1080;
//
//    public CreateUserPage(User user) {
//        super();
//        this.setContentPane(mainPanel);
//        this.setSize(new Dimension(WIDTH, HEIGHT));
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        this.setResizable(false);
//
//        roleC.setModel(new DefaultComboBoxModel<>(RoleEnum.values()));
//        specialtyCombobox.setModel(new DefaultComboBoxModel<>(SpecialtyEnum.values()));
//        specialtyCombobox.setEnabled(false);
//        scheduleComboBox.setEnabled(false);
//
//        this.setVisible(true);
//
//        addUserButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String username = usernameTf.getText();
//                String email = emailTf.getText();
//                String firstName = firstNameTf.getText();
//                String lastName = lastNameTf.getText();
//                String cnp = cnpTf.getText();
//                String phoneNo = phoneNoTf.getText();
//                RoleEnum selectedRole = (RoleEnum) roleC.getSelectedItem();
//                SpecialtyEnum selectedSpecialty = (SpecialtyEnum) specialtyCombobox.getSelectedItem();
//                int shift = scheduleComboBox.getSelectedIndex();
//
//                User existinguser = UserController.handleFindUserByEmail(email);
//                if (existinguser != null) {
//                    JOptionPane.showMessageDialog(mainPanel, "User with this email already exists!", "Error", JOptionPane.ERROR_MESSAGE);
//                }else {
//                    boolean userCreated = UserController.handleRegister(username, email, null, selectedRole, firstName, lastName, cnp, phoneNo , selectedSpecialty, shift);
//                    if (userCreated) {
//                        clearData();
//                        JOptionPane.showMessageDialog(mainPanel, "User created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
//                    } else {
//                        JOptionPane.showMessageDialog(mainPanel, "Error creating user. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
//                    }
//                }
//            }
//        });
//        backButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                dispose();
//                AdminDashboardPage adminDashboardPage = new AdminDashboardPage(user);
//            }
//        });
//        roleC.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Object selectedItem = roleC.getSelectedItem();
//                if (selectedItem instanceof RoleEnum) {
//                    RoleEnum selectedRole = (RoleEnum) selectedItem;
//                    if (selectedRole == RoleEnum.DOCTOR) {
//                        specialtyCombobox.setEnabled(true);
//                        scheduleComboBox.setEnabled(true);
//                    } else {
//                        specialtyCombobox.setEnabled(false);
//                        scheduleComboBox.setEnabled(false);
//                    }
//                }
//            }
//        });
//    }
//
//    private void createUIComponents() {
//        roleC = new JComboBox<>(RoleEnum.values());
//    }
//
//    private void clearData() {
//        usernameTf.setText("");
//        emailTf.setText("");
//        firstNameTf.setText("");
//        lastNameTf.setText("");
//        cnpTf.setText("");
//        phoneNoTf.setText("");
//        roleC.setSelectedIndex(0);
//    }
//}
