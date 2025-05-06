package com.mycompany.ums.gui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import com.mycompany.ums.db.DatabaseHandler;

public class UniversityRecordViewer {
    public void displayRecordList() {
        JFrame frame = new JFrame("University Records");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(textArea);

        try {
            Connection conn = DatabaseHandler.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM university");

            StringBuilder builder = new StringBuilder();
            while (rs.next()) {
                builder.append("ID: ").append(rs.getInt("id"))
                       .append(" | Name: ").append(rs.getString("name"))
                       .append(" | Location: ").append(rs.getString("location"))
                       .append("\n");
            }

            textArea.setText(builder.toString());

        } catch (SQLException e) {
            textArea.setText("Error loading records: " + e.getMessage());
        }

        frame.add(scroll);
        frame.setVisible(true);
    }
}
