package com.mycompany.ums.gui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;
import com.mycompany.ums.db.DatabaseHandler;

public class GPAViewer {
    public void displayRecordList() {
        JFrame frame = new JFrame("Student GPA Viewer");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(textArea);

        try {
            Connection conn = DatabaseHandler.getConnection();
            String sql = """
                SELECT s.student_id, u.username, c.credits, g.grade
                FROM grades g
                JOIN students s ON g.student_id = s.student_id
                JOIN users u ON s.user_id = u.id
                JOIN courses c ON g.course_code = c.course_code
                """;

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            Map<String, Double> gradeSum = new LinkedHashMap<>();
            Map<String, Integer> creditSum = new LinkedHashMap<>();
            Map<String, String> studentNames = new LinkedHashMap<>();

            while (rs.next()) {
                String id = rs.getString("student_id");
                String name = rs.getString("username");
                int credits = rs.getInt("credits");
                double grade = rs.getDouble("grade");

                gradeSum.put(id, gradeSum.getOrDefault(id, 0.0) + grade * credits);
                creditSum.put(id, creditSum.getOrDefault(id, 0) + credits);
                studentNames.put(id, name);
            }

            StringBuilder builder = new StringBuilder();
            for (String id : gradeSum.keySet()) {
                double gpa = gradeSum.get(id) / creditSum.get(id);
                builder.append("ID: ").append(id)
                       .append(" | Name: ").append(studentNames.get(id))
                       .append(" | GPA: ").append(String.format("%.2f", gpa))
                       .append("\n");
            }

            textArea.setText(builder.length() > 0 ? builder.toString() : "No GPA records found.");

        } catch (SQLException e) {
            textArea.setText("Error calculating GPA: " + e.getMessage());
        }

        frame.add(scroll);
        frame.setVisible(true);
    }
}

