

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.ums.gui;

import javax.swing.*;
import java.sql.*;
import com.mycompany.ums.db.DatabaseHandler;

public class ViewCollege {
    public void display() {
        JFrame frame = new JFrame("View Colleges");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> collegeList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(collegeList);

        try {
            Connection conn = DatabaseHandler.getConnection();
            if (conn == null) {
                JOptionPane.showMessageDialog(frame, "No database connection.");
                return;
            }

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM college");

            while (rs.next()) {
                listModel.addElement(rs.getString("name"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error loading colleges: " + e.getMessage());
        }

        frame.add(scrollPane);
        frame.setVisible(true);
    }
}
