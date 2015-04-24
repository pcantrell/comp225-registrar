package edu.macalester.registrar;

import java.util.*;


public class Course {
    public final static int NO_ENROLLMENT_LIMIT = Integer.MAX_VALUE;
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private int enrollmentLimit = NO_ENROLLMENT_LIMIT;
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

    public List<Student> getWaitList() {
        return Collections.unmodifiableList(waitList);
    }


    public int getEnrollmentLimit() {
        return enrollmentLimit;
    }

    public void setEnrollmentLimit(int newLimit) {
        if(newLimit < students.size()) {
            throw new IllegalArgumentException("Too many students already enrolled");
        }
        enrollmentLimit = newLimit;
        while(students.size() < enrollmentLimit && !waitList.isEmpty()) {
            waitList.remove().enrollIn(this);
        }
    }

    boolean enroll(Student student) {
        if (students.contains(student)) {
            return true;
        }
        if (waitList.contains(student)) {
            return false;
        }
        if (students.size() < enrollmentLimit) {
            students.add(student);
            student.enrollIn(this);
            return true;
        }
        waitList.add(student);
        return false;
    }

    void drop(Student student) {
        if (student.isEnrolledIn(this)) {
            student.drop(this);
        } else {
            students.remove(student);
            waitList.remove(student);
            if (students.size() < enrollmentLimit && !waitList.isEmpty()) {
                waitList.remove().enrollIn(this);
            }
        }
    }
}
