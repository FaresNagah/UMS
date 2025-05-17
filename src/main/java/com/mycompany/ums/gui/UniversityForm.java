package com.mycompany.ums.gui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import com.mycompany.ums.db.DatabaseHandler;

public class UniversityForm {
    public void display() {
        JFrame frame = new JFrame("add new University");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JLabel idLabel = new JLabel("University ID:");
        JTextField idField = new JTextField();

        JLabel nameLabel = new JLabel(" Name:");
        JTextField nameField = new JTextField();

        JLabel locationLabel = new JLabel(" Location:");
        JTextField locationField = new JTextField();

        JButton addButton = new JButton("add");

        addButton.addActionListener(e -> {
    String name = nameField.getText();
    String location = locationField.getText();

    try {
        Connection conn = DatabaseHandler.getConnection();
        if (conn != null) {
            String sql = "INSERT INTO university (name, location) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, location);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "University added successfully.");
             frame.dispose();
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error adding university: " + ex.getMessage());
    }
});


        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(locationLabel);
        panel.add(locationField);
        panel.add(addButton);

        frame.add(panel);
        frame.setVisible(true);
       

    }
    
}
