package com.mycompany.ums.gui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import com.mycompany.ums.db.DatabaseHandler;

public class DeleteStudentForm {
    public void display() {
        JFrame frame = new JFrame("Delete Student");
        frame.setSize(350, 200);
        frame.setLocationRelativeTo(null);

        JLabel idLabel = new JLabel("Enter Student ID to delete:");
        JTextField idField = new JTextField();

        JButton deleteButton = new JButton("Delete");

        deleteButton.addActionListener(e -> {
            String studentId = idField.getText().trim();
            try {
                Connection conn = DatabaseHandler.getConnection();
                String sql = "DELETE FROM students WHERE student_id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, studentId);
                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(frame, "Student deleted.");
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "No student found with that ID.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
            }
        });

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(idLabel);
        panel.add(idField);
        panel.add(deleteButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}
