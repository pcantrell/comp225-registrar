package edu.macalester.registrar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private ArrayList<Student> waitList = new ArrayList<Student>();
    private int enrollmentLimit;

    public int getEnrollmentLimit() { return enrollmentLimit; }

    public void setEnrollmentLimit(int enrollmentLimit) { this.enrollmentLimit = enrollmentLimit; }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    public ArrayList<Student> getWaitList() { return waitList; }

    void enroll(Student student) {
        students.add(student);
    }

    void drop(Student student) { students.remove(student); }

    void waitListEnroll(Student student){
        waitList.add(student);
    }


}
