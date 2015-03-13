package edu.macalester.registrar;

import java.util.*;


public class Course {
    private String catalogNumber, title;
    private int enrollmentLimit;
    private Set<Student> students = new HashSet<Student>();
    private Queue<Student> waitList = new LinkedList<Student>();

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
        students.add(student);
    }

    public Integer getEnrollmentLimit() { return enrollmentLimit; }

    public void setEnrollmentLimit(int enrollmentLimit) { this.enrollmentLimit = enrollmentLimit;}

    void addToWaitList(Student student) {
        waitList.add(student);
    }

    public Student getFirstPerson() {
        return waitList.poll();
    }

    public Queue getWaitList() {
        return waitList;
    }

    void drop(Student student){
        students.remove(student);
    }
}
