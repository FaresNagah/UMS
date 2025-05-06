package com.mycompany.ums.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import com.mycompany.ums.db.DatabaseHandler;
import java.sql.*;

public class DepartmentForm {
    public void display() {
        JFrame frame = new JFrame("Department Form");
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);

        JLabel deptLabel = new JLabel("Department Name:");
        JTextField deptField = new JTextField();

        JLabel uniLabel = new JLabel("Select University:");
        JComboBox<String> uniDropdown = new JComboBox<>();
        ArrayList<Integer> universityIds = new ArrayList<>();

        try {
            Connection conn = DatabaseHandler.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM university");
            while (rs.next()) {
                uniDropdown.addItem(rs.getString("name"));
                universityIds.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error loading universities: " + e.getMessage());
        }

        JButton saveButton = new JButton("Save");

        saveButton.addActionListener(e -> {
            String deptName = deptField.getText().trim();
            int selectedIndex = uniDropdown.getSelectedIndex();

            if (!deptName.isEmpty() && selectedIndex != -1) {
                int universityId = universityIds.get(selectedIndex);
                DatabaseHandler.insertDepartment(deptName, universityId);
                JOptionPane.showMessageDialog(frame, "Department saved to database!");
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "All fields are required!");
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.add(deptLabel);
        panel.add(deptField);
        panel.add(uniLabel);
        panel.add(uniDropdown);
        panel.add(saveButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}
