/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ums.gui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import com.mycompany.ums.db.DatabaseHandler;

public class uniUpdate {

    public void display() {
        JFrame frame = new JFrame("Update University");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JLabel idLabel = new JLabel("University ID:");
        JTextField idField = new JTextField();

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel locationLabel = new JLabel("Location:");
        JTextField locationField = new JTextField();

        JButton updateButton = new JButton("Update");

        updateButton.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String location = locationField.getText().trim();

            if (id.isEmpty() || name.isEmpty() || location.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                return;
            }

            try {
                Connection conn = DatabaseHandler.getConnection();
                if (conn != null) {
                    String sql = "UPDATE university SET name = ?, location = ? WHERE id = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, name);
                    stmt.setString(2, location);
                    stmt.setInt(3, Integer.parseInt(id));
                    int rowsAffected = stmt.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "University updated successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No university found with that ID.");
                    }
                    frame.dispose();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error updating university: " + ex.getMessage());
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "University ID must be a number.");
            }
        });

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(locationLabel);
        panel.add(locationField);
        panel.add(updateButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}

