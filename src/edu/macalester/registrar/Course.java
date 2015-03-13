package edu.macalester.registrar;

import java.util.*;

/**
 * Charles Park
 * COMP-225 Registrar Homework #1
 */
public class Course {
    private String catalogNumber, title;
    private int enrollmentLimit;
    private Set<Student> students = new HashSet<Student>();
    private List<Student> waitList = new LinkedList<Student>();

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

    public List<Student> getWaitList() {
        return Collections.unmodifiableList(waitList);
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
            if(!this.getStudents().contains(student) && !this.getWaitList().contains(student)) {
                waitList.add(student);
                throw new IllegalArgumentException("Attempting to over-enroll students!");
            }
        }
    }

    void drop(Student student) {
        if(this.getStudents().contains(student)) {
            students.remove(student);
            System.out.println(student.getName()
                    + " successfully dropped the course "
                    + this.getTitle()
                    + ".");
            if(this.getWaitList().size() > 0) {
                Student firstInLine = this.getWaitList().get(0);
                students.add(firstInLine);
                waitList.remove(firstInLine);
                System.out.println(firstInLine.getName()
                        + " will automatically be removed from the wait list and be enrolled for the course "
                        + this.getTitle()
                        + ".");
            }
        }
    }
}