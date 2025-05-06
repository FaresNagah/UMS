package com.mycompany.ums.models;

import java.util.ArrayList;
import java.util.HashMap;

public class Student extends User {
    private String studentID;
    private ArrayList<Course> courses = new ArrayList<>();
    private HashMap<String, Double> grades = new HashMap<>();

    public Student(String username, String email, String studentID) {
        super(username, email, "student");
        this.studentID = studentID;
    }

    public void enrollCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
            grades.put(course.getCourseCode(), 0.0);
        }
    }

    public void updateGrade(String courseCode, double grade) {
        grades.put(courseCode, grade);
    }

    public double calculateGPA() {
        if (grades.isEmpty()) return 0;
        double total = 0;
        for (double grade : grades.values()) {
            total += grade;
        }
        return total / grades.size();
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public String getStudentID() {
        return studentID;
    }
}

