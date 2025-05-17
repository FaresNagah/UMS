package com.mycompany.ums.models;
import java.util.ArrayList;

public class Student extends User {
    private String studentID;
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<CourseGrade> courseGrades = new ArrayList<>();

    public Student(String username, String email, String studentID) {
        super(username, email, "student");
        this.studentID = studentID;
    }

    public void enrollCourse(Course course) {
        boolean found = false;
        for (Course c : courses) {
            if (c.getCourseCode().equals(course.getCourseCode())) {
                found = true;
                break;
            }
        }

        if (!found) {
            courses.add(course);
            courseGrades.add(new CourseGrade(course, 0.0));
        }
    }

    public void updateGrade(String courseCode, double grade) {
        for (CourseGrade cg : courseGrades) {
            if (cg.getCourse().getCourseCode().equals(courseCode)) {
                cg.setGrade(grade);
            }
        }
    }

    public double calculateGPA() {
        if (courseGrades.size() == 0) {
            return 0;
        }

        double sum = 0;
        for (CourseGrade cg : courseGrades) {
            sum += cg.getGrade();
        }

        return sum / courseGrades.size();
    }
}