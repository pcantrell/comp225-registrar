package edu.macalester.registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private ArrayList<Student> waitList = new ArrayList<Student>();
    private int enrollmentLimit = 100000;

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public void setEnrollmentLimit(int newLimit) {
        this.enrollmentLimit = newLimit;
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

    public List<Student> getWaitList() {
        return Collections.unmodifiableList(waitList);
    }

    void addToWaitList(Student student){
        waitList.add(student);
    }

    void enroll(Student student) {
        if (students.size() < getEnrollmentLimit()) {
            students.add(student);
        } else {
            if (!students.contains(student)) {
                addToWaitList(student);
            }
        }
    }

    void drop(Student student) {
        if (students.contains(student)) {
            students.remove(student);
            students.add(waitList.get(0));
            waitList.remove(0);
        }
    }
}
