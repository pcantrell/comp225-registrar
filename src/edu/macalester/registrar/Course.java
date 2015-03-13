package edu.macalester.registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

/**
 * Student Name: Qinghao Peng
 */

public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private List<Student> waitlist = new ArrayList<Student>();
    private int enrollmentLimit;

    /*
        Getters and setters
     */
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

    public int getEnrollmentLimit() {
        return enrollmentLimit;
    }

    public void setEnrollmentLimit(int enrollmentLimit) {
        this.enrollmentLimit = enrollmentLimit;
    }

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    public List<Student> getWaitlist() {
        return Collections.unmodifiableList(waitlist);
    }

    /**
     * If the course is full, add the student to the waitlist.
     * If not, add the student to the course enrollment.
     * Notify the user if the student is added to the waitlist or enrollment.
     * @param student
     * @return
     */
    public boolean enroll(Student student) {
        if (students.size() + 1 <= enrollmentLimit) {
            students.add(student);
            System.out.println(student.getName() + " is added to " + catalogNumber);
            return true;
        } else {
            waitlist.add(student);
            System.out.println(student.getName() + " is added to the waitlist of " + catalogNumber);
            return false;
        }
    }

    /**
     * Drop a student from a class.
     * ONLY CALLED when the student voluntarily drops the class.
     * Then if the waitlist is not empty, let the first student enroll in the class,
     * and remove the student from the waitlist.
     * @param student
     */
    public void drop(Student student) {
        students.remove(student);
        if (!waitlist.isEmpty()) {
            Student newStudent = waitlist.get(0);
            waitlist.remove(0);
            newStudent.enrollIn(this);
        }
    }

}
