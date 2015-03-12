package edu.macalester.registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private Set<Student> waitList = new HashSet<Student>();
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

    public Set<Student> getWaitlist() {
        return Collections.unmodifiableSet(waitList);
    }

    void enroll(Student student) {
        if (students.size() < getEnrollmentLimit())
            students.add(student);
        else
            waitList.add(student);
            System.out.println("Enrollment limit hit for " + getTitle() +", student added to waitlist.");
    }
}
