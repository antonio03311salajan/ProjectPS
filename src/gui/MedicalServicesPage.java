package gui;

import model.MedicalService;
import model.User;
import presentation.MedicalServiceController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedicalServicesPage extends JFrame {
    private JPanel mainPanel;
    private JTable table1;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JTextField tfName;
    private JTextField tfPrice;
    private JTextField tfDuration;
    private JScrollPane scrollPane;
    private JPanel tablePanel;
    private JPanel buttonsPanel;
    private JPanel tfPanel;
    private JPanel topPanel;

    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1020;

    public MedicalServicesPage(User user) {
        super();
        this.setContentPane(mainPanel);
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        String[] columnNames = {"Id", "Name", "Price", "Duration"};
        DefaultTableModel tableModel1 = new DefaultTableModel(columnNames, 0);
        table1 = new JTable(tableModel1);
        table1.setRowHeight(30);

        scrollPane = new JScrollPane(table1);
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        updateButton = new JButton("Update");

        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(updateButton);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        topPanel.setLayout(new BorderLayout());
        topPanel.add(tfPanel, BorderLayout.WEST);

        refreshMedicalServices();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMedicalService();
                refreshMedicalServices();
            }
        });

        this.setVisible(true);
    }

    public void refreshMedicalServices() {
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);

        for (MedicalService service : MedicalServiceController.handleGetAllMedicalServices()) {
            model.addRow(new Object[]{
                    service.getId(),
                    service.getName(),
                    service.getPrice(),
                    service.getDuration()
            });
        }
    }

    public void addMedicalService() {
        try {
            String name = tfName.getText().trim();
            float price = Float.parseFloat(tfPrice.getText().trim());
            int duration = Integer.parseInt(tfDuration.getText().trim());

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Service name cannot be empty.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean success = MedicalServiceController.handleCreateMedicalService(name, price, duration);

            if (success) {
                JOptionPane.showMessageDialog(this, "Medical service added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                tfName.setText("");
                tfPrice.setText("");
                tfDuration.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add medical service.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for price and duration.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
