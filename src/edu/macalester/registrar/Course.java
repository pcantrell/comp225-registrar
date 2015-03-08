package edu.macalester.registrar;

import java.util.*;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private Queue<Student> waitList = new LinkedList<Student>();
    int enrollmentLimit = 2;

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

    public int getEnrollmentLimit() {
        return enrollmentLimit;
    }

    public Collection<Student> getWaitList() {
        return Collections.unmodifiableCollection(waitList);
    }

    public void addToWaitList(Student student){
        waitList.add(student);
    }

    public void dropStudent(Student student) {
        students.remove(student);
        waitList.poll().enrollIn(this);
    }
}
