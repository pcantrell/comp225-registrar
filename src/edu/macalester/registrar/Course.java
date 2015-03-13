package edu.macalester.registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private int enrollmentLimit = 10;
    private LinkedList<Student> waitList = new LinkedList<Student>();

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

    String enroll(Student student) {
        if (students.contains(student)) {
            return "already_enrolled";
        }
        if (waitList.contains(student)) {
            return "already_waitlisted";
        }
        if (students.size() < enrollmentLimit) {
            students.add(student);
            student.enrollIn(this);
            return "enrolled";
        }
        waitList.add(student);
        return "waitlisted";
    }

    void dropStudent(Student student) {
        if (student.isEnrolledIn(this)) {
            student.dropCourse(this);
        } else {
            students.remove(student);
            waitList.remove(student);
            if (students.size() < enrollmentLimit && waitList.size() > 0) {
                waitList.remove().enrollIn(this);
            }
        }
    }

    public int getEnrollmentLimit() {
        return enrollmentLimit;
    }
}
