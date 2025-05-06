package com.mycompany.ums.models;

import java.util.ArrayList;

public class University {
    private String name;
    private String location;
    private ArrayList<College> colleges = new ArrayList<>();
    private ArrayList<Department> departments = new ArrayList<>();

    public University(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public void addCollege(College college) {
        colleges.add(college);
    }

    public void addDepartment(Department department) {
        departments.add(department);
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<College> getColleges() {
        return colleges;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }
}
