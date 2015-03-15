package edu.macalester.registrar;

import java.util.*;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private int enrollmentLimit;
    private ArrayList<Student> waitList = new ArrayList<Student>();

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

    void enroll(Student student) {
        if(getStudents().size()<enrollmentLimit) {
            students.add(student);
        }
        else {
            waitList.add(student);
            throw(new RuntimeException());
        }
    }

    public void setenrollmentLimit(int enrollmentLimit) {
        this.enrollmentLimit = enrollmentLimit;
    }

    public int getEnrollmentLimit() {
        return enrollmentLimit;
    }

    public List<Student> getWaitList() {
        return Collections.unmodifiableList(waitList);
    }

    void unEnroll(Student student) {
        int size = getStudents().size();
        students.remove(student);
        if(size==enrollmentLimit) {
            waitList.get(0).enrollIn(this);
            waitList.remove(0);
        }
    }
}