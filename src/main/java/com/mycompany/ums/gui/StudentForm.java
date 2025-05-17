package com.mycompany.ums.gui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import com.mycompany.ums.db.DatabaseHandler;

public class StudentForm {
    public void display() {
        JFrame frame = new JFrame("add new Student Info");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JLabel idLabel = new JLabel("Student ID:");
        JTextField idField = new JTextField();

        JLabel nameLabel = new JLabel(" Username:");
        JTextField nameField = new JTextField();

        JLabel emailLabel = new JLabel(" Email:");
        JTextField emailField = new JTextField();

        JButton addButton = new JButton("update");

        addButton.addActionListener(e -> {
    String studentId = idField.getText();
    String username = nameField.getText();
    String email = emailField.getText();

    try {
        int userId = DatabaseHandler.insertUser(username, email, "student");
        if (userId != -1) {
            DatabaseHandler.insertStudent(studentId, userId);
            JOptionPane.showMessageDialog(null, "Student added successfully.");
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error adding student: " + ex.getMessage());
    }
});


        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(addButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}
