package com.mycompany.ums.gui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import com.mycompany.ums.db.DatabaseHandler;

public class GradeForm {
    public void display() {
        JFrame frame = new JFrame("Assign Grade");
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);

        JLabel studentLabel = new JLabel("Select Student:");
        JComboBox<String> studentDropdown = new JComboBox<>();
        ArrayList<String> studentIds = new ArrayList<>();

        JLabel courseLabel = new JLabel("Select Course:");
        JComboBox<String> courseDropdown = new JComboBox<>();
        ArrayList<String> courseCodes = new ArrayList<>();

        JLabel gradeLabel = new JLabel("Grade:");
        JTextField gradeField = new JTextField();

        try {
            Connection conn = DatabaseHandler.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs1 = stmt.executeQuery("SELECT DISTINCT student_id FROM enrollments");
            while (rs1.next()) {
                String sid = rs1.getString("student_id");
                studentDropdown.addItem(sid);
                studentIds.add(sid);
            }

            ResultSet rs2 = stmt.executeQuery("SELECT DISTINCT course_code FROM enrollments");
            while (rs2.next()) {
                String code = rs2.getString("course_code");
                courseDropdown.addItem(code);
                courseCodes.add(code);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error loading data: " + e.getMessage());
        }

        JButton saveButton = new JButton("Save Grade");

        saveButton.addActionListener(e -> {
            int sIndex = studentDropdown.getSelectedIndex();
            int cIndex = courseDropdown.getSelectedIndex();
            double grade;

            try {
                grade = Double.parseDouble(gradeField.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Grade must be a number.");
                return;
            }

            if (sIndex != -1 && cIndex != -1) {
                String studentId = studentIds.get(sIndex);
                String courseCode = courseCodes.get(cIndex);
                DatabaseHandler.insertGrade(studentId, courseCode, grade);
                JOptionPane.showMessageDialog(frame, "Grade saved.");
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
        panel.add(gradeLabel);
        panel.add(gradeField);
        panel.add(saveButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}
