package gui;

import model.User;

import javax.swing.*;
import java.awt.*;

public class ReceptionistDashboard extends JFrame {
    private JPanel mainPanel;
    private JTable patientsTable;
    private JTable doctorsTable;
    private JTable serviceTable;
    private JButton validateButton;

    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;

    public ReceptionistDashboard(User user) {
        super();
        this.setContentPane(mainPanel);
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }
}
