package gui;

import model.User;
import presentation.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    private JPanel mainPanel;
    private JTextField emailTf;
    private JButton logInButton;
    private JButton signUpButton;
    private JPasswordField passwordF;
    private static final int WIDTH=1920;
    private static final int HEIGHT=1020;

    public LoginPage() {
        super();
        this.setContentPane(mainPanel);
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailTf.getText();
                String password = new String(passwordF.getPassword());
                User loggedUser = UserController.handleLogin(email, password);
                if(loggedUser != null) {
                    dispose();
                    switch (loggedUser.getRole()) {
                        case ADMIN:
                            AdminDashboardPage adminDashboardPage = new AdminDashboardPage(loggedUser);
                            break;
                        case USER:
                            UserPage userDashboardPage = new UserPage(loggedUser);
                            break;
                        case RECEPTIONIST:
                            ReceptionistDashboard receptionistDashboard = new ReceptionistDashboard(loggedUser);
                            break;
                        default:
                            System.out.println("Unknown role, please contact support.");
                            break;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials");
                }
            }
        });
    }

}