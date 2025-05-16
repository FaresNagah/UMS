package com.mycompany.ums.gui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import com.mycompany.ums.db.DatabaseHandler;

public class CourseForm {
    private JTextField departmentField;
    private JTextField creditsField;

    public void display() {
        JFrame frame = new JFrame("Add New Course Info");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JLabel codeLabel = new JLabel("Course Code:");
        JTextField codeField = new JTextField();

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel creditLabel = new JLabel("Credits:");
        creditsField = new JTextField();

        JLabel deptLabel = new JLabel("Department:");
        departmentField = new JTextField();

        JButton addButton = new JButton("Add");

        addButton.addActionListener(e -> {
            String code = codeField.getText().trim();
            String name = nameField.getText().trim();
            String dept = departmentField.getText().trim();
            int credits = 0;

            try {
                credits = Integer.parseInt(creditsField.getText().trim());
                Connection conn = DatabaseHandler.getConnection();
                if (conn != null) {
                    String sql = "INSERT INTO courses (course_code, name, credits, department) VALUES (?, ?, ?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, code);
                    stmt.setString(2, name);
                    stmt.setInt(3, credits);
                    stmt.setString(4, dept);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(frame, "Course added successfully.");
                    frame.dispose(); 
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(frame, "Credits must be a number.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error adding course: " + ex.getMessage());
            }
        });

        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panel.add(codeLabel);
        panel.add(codeField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(creditLabel);
        panel.add(creditsField);
        panel.add(deptLabel);
        panel.add(departmentField);
        panel.add(addButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}
