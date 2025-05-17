package com.mycompany.ums.db;

import java.sql.*;
import javax.swing.*;

public class DatabaseHandler {
    private static Connection conn;

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:ums.db");
            createTables();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "SQLite JDBC driver not found.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database connection failed: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Error: Database connection is not established.");
        }
        return conn;
    }

    private static void createTables() {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS university (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, location TEXT)");
            stmt.execute("CREATE TABLE IF NOT EXISTS departments (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, university_id INTEGER)");
            stmt.execute("CREATE TABLE IF NOT EXISTS college (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)");
            stmt.execute("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, email TEXT, role TEXT)");
            stmt.execute("CREATE TABLE IF NOT EXISTS students (student_id TEXT PRIMARY KEY, user_id INTEGER)");
          
      stmt.execute("DROP TABLE IF EXISTS staff");
stmt.execute("CREATE TABLE IF NOT EXISTS staff (staff_id TEXT PRIMARY KEY, user_id INTEGER, department TEXT, university_id INTEGER)");

            stmt.execute("CREATE TABLE IF NOT EXISTS courses (course_code TEXT PRIMARY KEY, name TEXT, credits INTEGER, department TEXT)");
            stmt.execute("CREATE TABLE IF NOT EXISTS enrollments (student_id TEXT, course_code TEXT)");
            stmt.execute("CREATE TABLE IF NOT EXISTS grades (student_id TEXT, course_code TEXT, grade REAL)");
            stmt.execute("CREATE TABLE IF NOT EXISTS teaches (staff_id TEXT, course_code TEXT)");
           

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error creating tables: " + e.getMessage());
        }
    }

    public static int insertUser(String username, String email, String role) {
        try {
            String sql = "INSERT INTO users (username, email, role) VALUES (?, ?, ?)";
            PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, role);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Insert User failed: " + e.getMessage());
        }
        return -1;
    }

    public static void insertStudent(String studentId, int userId) {
        try {
            String sql = "INSERT INTO students (student_id, user_id) VALUES (?, ?)";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, studentId);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Insert Student failed: " + e.getMessage());
        }
    }

    public static void insertStaff(String staffId, int userId, String department, int universityId) {
    try {
        String sql = "INSERT INTO staff (staff_id, user_id, department, university_id) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setString(1, staffId);
        stmt.setInt(2, userId);
        stmt.setString(3, department);
        stmt.setInt(4, universityId);
        stmt.executeUpdate();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Insert Staff failed: " + e.getMessage());
    }
}

    public static DefaultComboBoxModel<String> getUniversityNames() {
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
    try {
        String sql = "SELECT id, name FROM university";
        Statement stmt = getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            model.addElement(id + " - " + name); 
        }
        rs.close();
        stmt.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Failed to load universities: " + e.getMessage());
    }
    return model;
}


    public static void insertGrade(String studentId, String courseCode, double grade) {
        try {
            String sql = "INSERT INTO grades (student_id, course_code, grade) VALUES (?, ?, ?)";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, studentId);
            stmt.setString(2, courseCode);
            stmt.setDouble(3, grade);
            stmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Insert Grade failed: " + e.getMessage());
        }
    }

    public static void insertEnrollment(String studentId, String courseCode) {
        try {
            String sql = "INSERT INTO enrollments (student_id, course_code) VALUES (?, ?)";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, studentId);
            stmt.setString(2, courseCode);
            stmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Insert Enrollment failed: " + e.getMessage());
        }
    }
    
    public static void insertCollege(String name) {
    try {
        String sql = "INSERT INTO college (name) VALUES (?)";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setString(1, name);
        stmt.executeUpdate();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Insert College failed: " + e.getMessage());
    }
}




    public static void insertTeaches(String staffId, String courseCode) {
        try {
            String sql = "INSERT INTO teaches (staff_id, course_code) VALUES (?, ?)";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, staffId);
            stmt.setString(2, courseCode);
            stmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Insert Teaches failed: " + e.getMessage());
        }
    }

    public static void insertDepartment(String name, int universityId) {
        try {
            String sql = "INSERT INTO departments (name, university_id) VALUES (?, ?)";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setInt(2, universityId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Insert Department failed: " + e.getMessage());
        }
    }
}
