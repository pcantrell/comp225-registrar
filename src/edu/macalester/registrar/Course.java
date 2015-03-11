package edu.macalester.registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Charles Park
 * COMP-225 Registrar Homework #1
 */
public class Course {
    private String catalogNumber, title;
    private int enrollmentLimit;
    private Set<Student> students = new HashSet<Student>();
    private Set<Student> waitList = new HashSet<Student>();

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

    public int getEnrollmentLimit() { return enrollmentLimit; }

    public void setEnrollmentLimit(int enrollmentLimit) { this.enrollmentLimit = enrollmentLimit; }

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    public Set<Student> getWaitList() {
        return Collections.unmodifiableSet(waitList);
    }

    void enroll(Student student) {
        if(this.getStudents().size() < this.getEnrollmentLimit()) {
            students.add(student);
            System.out.println("Enrollment was successful for student "
                    + student.getName()
                    + " for course "
                    + this.getTitle()
                    + ".");
        } else {
            if (!this.getStudents().contains(student)) {
                waitList.add(student);
                System.out.println(student.getName()
                        + " will automatically be placed on the waiting list for the course "
                        + this.getTitle()
                        + ".");
//            throw new IllegalArgumentException("Course: Attempting to over-enroll students!");
            }
        }

    }
}
