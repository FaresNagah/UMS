package com.mycompany.ums.gui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import com.mycompany.ums.db.DatabaseHandler;

public class EnrollmentForm {
    public void display() {
        JFrame frame = new JFrame("Enroll Student in Course");
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);

        JLabel studentLabel = new JLabel("Select Student:");
        JComboBox<String> studentDropdown = new JComboBox<>();
        ArrayList<String> studentIds = new ArrayList<>();

        JLabel courseLabel = new JLabel("Select Course:");
        JComboBox<String> courseDropdown = new JComboBox<>();
        ArrayList<String> courseCodes = new ArrayList<>();

        try {
            Connection conn = DatabaseHandler.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs1 = stmt.executeQuery("SELECT student_id FROM students");
            while (rs1.next()) {
                String sid = rs1.getString("student_id");
                studentDropdown.addItem(sid);
                studentIds.add(sid);
            }

            ResultSet rs2 = stmt.executeQuery("SELECT course_code FROM courses");
            while (rs2.next()) {
                String code = rs2.getString("course_code");
                courseDropdown.addItem(code);
                courseCodes.add(code);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error loading data: " + e.getMessage());
        }

        JButton enrollButton = new JButton("Enroll");

        enrollButton.addActionListener(e -> {
            int sIndex = studentDropdown.getSelectedIndex();
            int cIndex = courseDropdown.getSelectedIndex();

            if (sIndex != -1 && cIndex != -1) {
                String studentId = studentIds.get(sIndex);
                String courseCode = courseCodes.get(cIndex);
                DatabaseHandler.insertEnrollment(studentId, courseCode);
                JOptionPane.showMessageDialog(frame, "Student enrolled in course.");
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Selection missing.");
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.add(studentLabel);
        panel.add(studentDropdown);
        panel.add(courseLabel);
        panel.add(courseDropdown);
        panel.add(enrollButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}
