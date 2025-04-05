package gui;

import model.User;

import javax.swing.*;
import java.awt.*;

public class UserPage extends JFrame{
    private JLabel usernameTf;
    private JLabel emailTf;
    private JLabel firstnameTf;
    private JLabel cnpTf;
    private JLabel phonenumberTf;
    private JLabel lastnameTf;
    private JPanel mainPanel;
    private JTable table1;
    public static final int WIDTH=1920;
    public static final int HEIGHT=1080;

    public UserPage(User user) {
        super();
        this.setContentPane(mainPanel);
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        usernameTf.setText(user.getUsername());
        emailTf.setText(user.getEmail());
        firstnameTf.setText(user.getFirstName());
        lastnameTf.setText(user.getLastName());
        cnpTf.setText(user.getCnp());
        phonenumberTf.setText(user.getPhoneNumber());
    }
}
