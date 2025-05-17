package com.mycompany.ums.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.mycompany.ums.db.DatabaseHandler;

public class UpdateCourseForm {
    private JFrame frame;
    private JComboBox<String> courseDropdown;
    private JTextField nameField;
    private JTextField creditsField;
    private JTextField departmentField;

    public void display() {
        frame = new JFrame("Update Course Info");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        courseDropdown = new JComboBox<>();
       

        nameField = new JTextField();
        creditsField = new JTextField();
        departmentField = new JTextField();
        
        loadCourseCodes(); 
        
        courseDropdown.addActionListener(e -> loadCourseDetails((String) courseDropdown.getSelectedItem()));

        JButton updateButton = new JButton("Update Course");
        updateButton.addActionListener(e -> updateCourse());

        panel.add(new JLabel("Select Course Code:"));
        panel.add(courseDropdown);
        panel.add(new JLabel("Course Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Credits:"));
        panel.add(creditsField);
        panel.add(new JLabel("Department:"));
        panel.add(departmentField);
        panel.add(updateButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void loadCourseCodes() {
        try {
            Connection conn = DatabaseHandler.getConnection();
            String sql = "SELECT course_code FROM courses";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                courseDropdown.addItem(rs.getString("course_code"));
            }

            if (courseDropdown.getItemCount() > 0) {
                loadCourseDetails((String) courseDropdown.getSelectedItem());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error loading courses: " + e.getMessage());
        }
    }

    private void loadCourseDetails(String courseCode) {
        try {
            Connection conn = DatabaseHandler.getConnection();
            String sql = "SELECT * FROM courses WHERE course_code = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, courseCode);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                nameField.setText(rs.getString("name"));
                creditsField.setText(String.valueOf(rs.getInt("credits")));
                departmentField.setText(rs.getString("department"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error loading course details: " + e.getMessage());
        }
    }

    private void updateCourse() {
        String courseCode = (String) courseDropdown.getSelectedItem();
        String name = nameField.getText().trim();
        String dept = departmentField.getText().trim();
        int credits = 0;

        try {
            credits = Integer.parseInt(creditsField.getText().trim());
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(frame, "Credits must be a valid number.");
            return;
        }

        try {
            Connection conn = DatabaseHandler.getConnection();
            String sql = "UPDATE courses SET name = ?, credits = ?, department = ? WHERE course_code = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setInt(2, credits);
            stmt.setString(3, dept);
            stmt.setString(4, courseCode);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(frame, "Course updated successfully.");
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "No course updated. Please check the course code.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error updating course: " + e.getMessage());
        }
    }
}
