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

    public static final int NO_ENROLLMENT_LIMIT = Integer.MAX_VALUE;

    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private List<Student> waitlist = new ArrayList<Student>();
    private int enrollmentLimit = NO_ENROLLMENT_LIMIT;

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
        // difference refers to the number of spots in the class
        int difference = enrollmentLimit - students.size();

        if (difference >= 0) {
            this.enrollmentLimit = enrollmentLimit;
            // enroll the students from waitlist until no spot is left or waitlist is empty
            while ((!waitlist.isEmpty()) && (difference > 0)) {
                waitlist.get(0).enrollIn(this);
                difference--;
            }
        } else {
            System.out.println("Don't set the enrollment list lower than the current student size!");
            throw new IllegalArgumentException();
        }
    }

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    public List<Student> getWaitList() {
        return Collections.unmodifiableList(waitlist);
    }

    /**
     * If the course is full, add the student to the waitlist.
     * If not, add the student to the course enrollment.
     * Notify the user if the student is added to the waitlist or enrollment.
     */
    public boolean enroll(Student student) {

        if (students.size() + 1 <= enrollmentLimit) {
            if (!students.contains(student)) {
                students.add(student);
                System.out.println(student.getName() + " is added to " + catalogNumber);
            }
            if (waitlist.contains(student)) { waitListDrop(student); }
            return true;
        } else {
            if (!waitlist.contains(student)) {
                waitlist.add(student);
                System.out.println(student.getName() + " is added to the waitlist of " + catalogNumber);
            }
            return false;
        }
    }

    /**
     * Drop a student from a class.
     * ONLY CALLED when the student voluntarily drops the class.
     * Then if the waitlist is not empty, let the first student enroll in the class,
     * and remove the student from the waitlist.
     */
    public void drop(Student student) {
        if (students.contains(student)) {
            students.remove(student);
        }
        if (!waitlist.isEmpty()) {
            Student newStudent = waitlist.get(0);
            waitlist.remove(0);
            newStudent.enrollIn(this);
        }
    }

    public void waitListDrop(Student student) {
        waitlist.remove(student);
    }

}
