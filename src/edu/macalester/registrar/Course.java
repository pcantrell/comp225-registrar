package edu.macalester.registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;
import java.util.List;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private List<Student> waitlist = new LinkedList<Student>();

    private int enrollmentLimit = Integer.MAX_VALUE;

    public static final int NO_ENROLLMENT_LIMIT = Integer.MAX_VALUE;


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

    public int getEnrollmentLimit() {
        return enrollmentLimit;
    }

    public void setEnrollmentLimit(int enrollmentLimit) {

        if (enrollmentLimit < this.students.size()) {
            throw new IllegalArgumentException();
        }

        this.enrollmentLimit = enrollmentLimit;

        shiftFromWaitlistToEnrolled();
    }

    public List<Student> getWaitList() {
        return Collections.unmodifiableList(waitlist);
    }

    boolean enroll(Student student) {
        if (students.contains(student)) {
            return true; // student already enrolled
        } else {
            if (waitlist.contains(student)) {
                return false; // student already waitlisted
            } else {
                if (students.size() < enrollmentLimit) {
                    students.add(student);
                    return true;
                } else {
                    waitlist.add(student);
                    return false;
                }
            }
        }
    }

    void drop(Student student) {
        waitlist.remove(student);
        if (students.remove(student)) {
            shiftFromWaitlistToEnrolled();
        }
    }

    private void shiftFromWaitlistToEnrolled() {
        while (this.students.size() < this.enrollmentLimit && this.waitlist.size() > 0) {
            Student student = this.waitlist.remove(0);
            student.enrollIn(this);
        }
    }

}
