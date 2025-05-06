package com.mycompany.ums.gui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import com.mycompany.ums.db.DatabaseHandler;


public class CourseForm {
    private JTextField creditsField;
private JTextField departmentField;
    public void display() {
        JFrame frame = new JFrame("add new Course Info");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        creditsField = new JTextField(20);
departmentField = new JTextField(20);

        JLabel codeLabel = new JLabel("Course Code:");
        JTextField codeField = new JTextField();

        JLabel nameLabel = new JLabel(" Name:");
        JTextField nameField = new JTextField();

        JLabel creditLabel = new JLabel(" Credits:");
        JTextField creditField = new JTextField();

        JButton addButton = new JButton("add");

       addButton.addActionListener(e -> {
    String code = codeField.getText();
    String name = nameField.getText();
    int credits = Integer.parseInt(creditsField.getText());
    String department = departmentField.getText();

    try {
        Connection conn = DatabaseHandler.getConnection();
        if (conn != null) {
            String sql = "INSERT INTO courses (course_code, name, credits, department) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, code);
            stmt.setString(2, name);
            stmt.setInt(3, credits);
            stmt.setString(4, department);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Course added successfully.");
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error adding course: " + ex.getMessage());
    }
});


        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(codeLabel);
        panel.add(codeField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(creditLabel);
        panel.add(creditField);
        panel.add(addButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}
