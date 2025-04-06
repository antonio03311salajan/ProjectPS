package gui;

import model.User;
import presentation.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateUserPage extends JFrame{
    private JPanel mainP;
    private JTextField usernameTf;
    private JTextField emailTf;
    private JTextField firstnameTf;
    private JTextField lastnameTf;
    private JTextField phoneNoTf;
    private JButton updateButton;
    private JButton backButton;
    public static final int WIDTH=1720;
    public static final int HEIGHT=1020;

    public UpdateUserPage(User editor, User user) {
        super();
        this.setContentPane(mainP);
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

        usernameTf.setText(user.getUsername());
        emailTf.setText(user.getEmail());
        firstnameTf.setText(user.getFirstName());
        lastnameTf.setText(user.getLastName());
        phoneNoTf.setText(user.getPhoneNumber());

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserController.handleUpdateUser(user.getId(),usernameTf.getText(),emailTf.getText(),firstnameTf.getText(),lastnameTf.getText(),phoneNoTf.getText());
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdminDashboardPage adminDashboardPage = new AdminDashboardPage(editor);
            }
        });
    }
}
