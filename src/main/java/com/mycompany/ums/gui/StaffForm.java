package com.mycompany.ums.gui;

import javax.swing.*;
import java.awt.*;
import com.mycompany.ums.db.DatabaseHandler;

public class StaffForm {
    public void display() {
        JFrame frame = new JFrame("Staff Registration");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JLabel nameLabel = new JLabel("Username:");
        JTextField nameField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JLabel idLabel = new JLabel("Staff ID:");
        JTextField idField = new JTextField();

        JLabel deptLabel = new JLabel("Department:");
        JTextField deptField = new JTextField();

        JButton saveButton = new JButton("Save");

        saveButton.addActionListener(e -> {
            String username = nameField.getText().trim();
            String email = emailField.getText().trim();
            String staffId = idField.getText().trim();
            String department = deptField.getText().trim();

            if (!username.isEmpty() && !email.isEmpty() && !staffId.isEmpty() && !department.isEmpty()) {
                int userId = DatabaseHandler.insertUser(username, email, "staff");
                if (userId != -1) {
                    DatabaseHandler.insertStaff(staffId, userId, department);
                    JOptionPane.showMessageDialog(frame, "Staff member saved to database!");
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to create user. Email or username may be duplicate.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "All fields are required!");
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(deptLabel);
        panel.add(deptField);
        panel.add(saveButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}

