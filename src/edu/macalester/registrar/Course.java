package edu.macalester.registrar;

import java.lang.reflect.Array;

import java.util.Queue;
import java.util.*;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private Queue<Student> waitList = new LinkedList<Student>();

    private int enrollmentLimit;

    public int getEnrollmentLimit() { return this.enrollmentLimit; }

    public void setEnrollmentLimit(int enrollLimit) { this.enrollmentLimit= enrollLimit; }

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

    public Set<Student> getStudents() { return Collections.unmodifiableSet(students); }

    public Queue<Student> getWaitList() { return waitList; }

    void enroll(Student student) {
            students.add(student);
    }

    void addToWaitList(Student student) {
        waitList.add(student);
    }

    void drop(Student student) {
        if (students.size() == this.enrollmentLimit) {
            students.remove(student);
            Student newStudent = waitList.remove();
            newStudent.enrollIn(this);
        } else { students.remove(student); }
    }
}
