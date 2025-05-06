package com.mycompany.ums.models;

import java.util.ArrayList;

public class Course {
    private String courseCode;
    private String name;
    private int credits;
    private String department;
    private ArrayList<Student> enrolledStudents = new ArrayList<>();

    public Course(String courseCode, String name, int credits, String department) {
        this.courseCode = courseCode;
        this.name = name;
        this.credits = credits;
        this.department = department;
    }

    public void enrollStudent(Student student) {
        if (!enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
        }
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public String getDepartment() {
        return department;
    }

    public ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }
}
