package edu.macalester.registrar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.List;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private ArrayList<Student> waitList = new ArrayList<Student>();
    private int enrollmentLimit = 1;

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public String getTitle() {
        return title;
    }

    public int getEnrollmentLimit() {
        return enrollmentLimit;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    public List<Student> getWaitlist() {
        return waitList;
    }

    void enroll(Student student) {
        if (students.size() < getEnrollmentLimit())  //if the course is not full
            students.add(student);                  //add student
        else
        if (!students.contains(student))  //if student is not enrolled and enrollment is full
            waitList.add(student);       // add student to waitList
    }
}
