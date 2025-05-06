package com.mycompany.ums.gui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import com.mycompany.ums.db.DatabaseHandler;

public class AssignCourseForm {
    public void display() {
        JFrame frame = new JFrame("Assign Course to Staff");
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);

        JLabel staffLabel = new JLabel("Select Staff:");
        JComboBox<String> staffDropdown = new JComboBox<>();
        ArrayList<String> staffIds = new ArrayList<>();

        JLabel courseLabel = new JLabel("Select Course:");
        JComboBox<String> courseDropdown = new JComboBox<>();
        ArrayList<String> courseCodes = new ArrayList<>();

        try {
            Connection conn = DatabaseHandler.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs1 = stmt.executeQuery("SELECT staff_id FROM staff");
            while (rs1.next()) {
                String sid = rs1.getString("staff_id");
                staffDropdown.addItem(sid);
                staffIds.add(sid);
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

        JButton assignButton = new JButton("Assign");

        assignButton.addActionListener(e -> {
            int sIndex = staffDropdown.getSelectedIndex();
            int cIndex = courseDropdown.getSelectedIndex();

            if (sIndex != -1 && cIndex != -1) {
                String staffId = staffIds.get(sIndex);
                String courseCode = courseCodes.get(cIndex);
                DatabaseHandler.insertTeaches(staffId, courseCode);
                JOptionPane.showMessageDialog(frame, "Course assigned to staff.");
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Selection missing.");
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.add(staffLabel);
        panel.add(staffDropdown);
        panel.add(courseLabel);
        panel.add(courseDropdown);
        panel.add(assignButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}
