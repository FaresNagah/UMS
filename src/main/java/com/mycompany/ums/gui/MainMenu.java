package com.mycompany.ums.gui;

import javax.swing.*;
import java.awt.*;

public class MainMenu {
    public void displayMainMenu() {
        JFrame frame = new JFrame("University Management System");
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JButton uniBtn = new JButton("Add University");
        JButton collegeBtn = new JButton("Add College");
        JButton deptBtn = new JButton("Add Department");
        JButton studentBtn = new JButton("Add Student");
        JButton staffBtn = new JButton("Add Staff");
        JButton courseBtn = new JButton("Add Course");
        JButton enrollBtn = new JButton("Enroll Student");
        JButton gradeBtn = new JButton("Assign Grade");
        JButton assignBtn = new JButton("Assign Course to Staff");
        JButton exitBtn = new JButton("Exit");

        JButton viewUniBtn = new JButton("View Universities");
        JButton viewCollegeBtn = new JButton("View Colleges");
     
    
        JButton viewStudentBtn = new JButton("View Students");
        JButton viewCourseBtn = new JButton("View Courses");
        JButton viewGpaBtn = new JButton("View Student GPAs");

        JButton deleteStudentBtn = new JButton("Delete Student");
        JButton updateUniBtn = new JButton("Update University");
        JButton updateStudentBtn = new JButton("Update Student");
        JButton updateCourseBtn = new JButton("Update Course");


        uniBtn.addActionListener(e -> new UniversityForm().display());
        collegeBtn.addActionListener(e -> new CollegeForm().display());
        deptBtn.addActionListener(e -> new DepartmentForm().display());
        studentBtn.addActionListener(e -> new StudentForm().display());
        staffBtn.addActionListener(e -> new StaffForm().display());
        courseBtn.addActionListener(e -> new CourseForm().display());
        enrollBtn.addActionListener(e -> new EnrollmentForm().display());
        gradeBtn.addActionListener(e -> new GradeForm().display());
        assignBtn.addActionListener(e -> new AssignCourseForm().display());
        exitBtn.addActionListener(e -> System.exit(0));

        viewUniBtn.addActionListener(e -> new UniversityRecordViewer().displayRecordList());
        viewCollegeBtn.addActionListener(e -> new ViewCollege().display());
        viewStudentBtn.addActionListener(e -> new StudentRecordViewer().displayRecordList());
        viewCourseBtn.addActionListener(e -> new CourseRecordViewer().displayRecordList());
        viewGpaBtn.addActionListener(e -> new GPAViewer().displayRecordList());

        deleteStudentBtn.addActionListener(e -> new DeleteStudentForm().display());
        updateUniBtn.addActionListener(e -> new uniUpdate().display());
        updateStudentBtn.addActionListener(e -> new StudentForm().display());
        updateCourseBtn.addActionListener(e -> {new UpdateCourseForm().display();});

        panel.add(uniBtn);
        panel.add(collegeBtn);
        panel.add(deptBtn);
        panel.add(studentBtn);
        panel.add(staffBtn);
        panel.add(courseBtn);
        panel.add(enrollBtn);
        panel.add(gradeBtn);
        panel.add(assignBtn);

        panel.add(viewUniBtn);
        panel.add(viewCollegeBtn);
        panel.add(viewStudentBtn);
        panel.add(viewCourseBtn);
        panel.add(viewGpaBtn);

        panel.add(updateUniBtn);
        panel.add(updateStudentBtn);
        panel.add(updateCourseBtn);
        panel.add(deleteStudentBtn);

        panel.add(exitBtn);

        frame.add(panel);
        frame.setVisible(true);
    }
}

