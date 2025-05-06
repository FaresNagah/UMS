package com.mycompany.ums.gui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import com.mycompany.ums.db.DatabaseHandler;

public class CourseRecordViewer {
    public void displayRecordList() {
        JFrame frame = new JFrame("Course Records");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(textArea);

        try {
            Connection conn = DatabaseHandler.getConnection();
            String sql = "SELECT * FROM courses";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            StringBuilder builder = new StringBuilder();
            while (rs.next()) {
                builder.append("Code: ").append(rs.getString("course_code"))
                       .append(" | Name: ").append(rs.getString("name"))
                       .append(" | Credits: ").append(rs.getInt("credits"))
                       .append(" | Dept: ").append(rs.getString("department"))
                       .append("\n");
            }

            textArea.setText(builder.toString());

        } catch (SQLException e) {
            textArea.setText("Error loading courses: " + e.getMessage());
        }

        frame.add(scroll);
        frame.setVisible(true);
    }
}
