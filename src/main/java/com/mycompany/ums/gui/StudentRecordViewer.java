package com.mycompany.ums.gui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import com.mycompany.ums.db.DatabaseHandler;

public class StudentRecordViewer {
    public void displayRecordList() {
        JFrame frame = new JFrame("Student Records");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(textArea);

        try {
            Connection conn = DatabaseHandler.getConnection();
            String sql = "SELECT s.student_id, u.username, u.email " +
                         "FROM students s JOIN users u ON s.user_id = u.id";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            StringBuilder builder = new StringBuilder();
            while (rs.next()) {
                builder.append("ID: ").append(rs.getString("student_id"))
                       .append(" | Name: ").append(rs.getString("username"))
                       .append(" | Email: ").append(rs.getString("email"))
                       .append("\n");
            }

            textArea.setText(builder.toString());

        } catch (SQLException e) {
            textArea.setText("Error loading students: " + e.getMessage());
        }

        frame.add(scroll);
        frame.setVisible(true);
    }
}
