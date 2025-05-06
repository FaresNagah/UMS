package com.mycompany.ums.models;

import java.util.ArrayList;

public class AcademicStaff extends User {
    private String staffID;
    private String department;
    private ArrayList<Course> courses = new ArrayList<>();

    public AcademicStaff(String username, String email, String staffID, String department) {
        super(username, email, "staff");
        this.staffID = staffID;
        this.department = department;
    }

    public void teachCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
        }
    }

    public String getStaffID() {
        return staffID;
    }

    public String getDepartment() {
        return department;
    }

    public ArrayList<Course> getCoursesTaught() {
        return courses;
    }
}
