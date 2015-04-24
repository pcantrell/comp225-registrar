package edu.macalester.registrar;

import java.util.*;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students;
    private int enrollmentLimit;
    private ArrayList<Student> waitList;

    public static final int NO_ENROLLMENT_LIMIT = Integer.MAX_VALUE;

    public Course() {
        students = new HashSet<Student>();
        waitList = new ArrayList<Student>();
        enrollmentLimit = NO_ENROLLMENT_LIMIT;
    }

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
        if(getStudents().size()<enrollmentLimit) {
            students.add(student);
        }
        else if (!waitList.contains(student)){
            waitList.add(student);
            throw new RuntimeException();
        }
        else {
            throw new RuntimeException();
        }
    }

    public void setEnrollmentLimit(int enrollmentLimit) {
        if (enrollmentLimit >= getStudents().size()) {
            this.enrollmentLimit = enrollmentLimit;
            while (students.size() < enrollmentLimit && waitList.size() > 0) {
                waitList.get(0).enrollIn(this);
                waitList.remove(0);
            }
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public int getEnrollmentLimit() {
        return enrollmentLimit;
    }

    public List<Student> getWaitList() {
        return Collections.unmodifiableList(waitList);
    }

    void unEnroll(Student student) {
        students.remove(student);
        waitList.remove(student);
        if(students.size()<enrollmentLimit) {
            if (!waitList.isEmpty()) {
                waitList.get(0).enrollIn(this);
                waitList.remove(0);
            }
        }
    }
}