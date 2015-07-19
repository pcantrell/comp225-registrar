package edu.macalester.registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.LinkedList;


public class Course {
    public static int NO_ENROLLMENT_LIMIT = Integer.MAX_VALUE;
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private int enrollmentLimit = NO_ENROLLMENT_LIMIT;
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

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    public List<Student> getWaitList() {
        return Collections.unmodifiableList(waitList);
    }

    public void setEnrollmentLimit(int enrollmentLimit) {
        if (enrollmentLimit < students.size()) {
            throw new IllegalArgumentException("Cannot set enrollment limit below class size.");
        }
        this.enrollmentLimit = enrollmentLimit;
        while (this.getStudents().size() < enrollmentLimit && waitList.size() > 0) {
            Student waitingStudent = waitList.get(0);
            waitingStudent.enrollIn(this);
            students.add(waitingStudent);
            waitList.remove(0);
        }
    }

    public int getEnrollmentLimit() {
        return this.enrollmentLimit;
    }

    void enroll(Student student) {
        if (students.size() >= enrollmentLimit) {
            if (!waitList.contains(student)) {
                waitList.add(student);
            }
        } else {
            students.add(student);
        }
    }

    void drop(Student student) {
        students.remove(student);
        waitList.remove(student);
        if ((waitList.size() > 0) && (students.size() < enrollmentLimit)) {
            students.add(waitList.get(0));
            waitList.remove(0);
        }
    }
}
