package edu.macalester.registrar;

import java.lang.reflect.Array;

import java.util.Queue;
import java.util.*;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private Queue<Student> waitList = new LinkedList<Student>();
    private int enrollmentLimit = NO_ENROLLMENT_LIMIT;
    public static int NO_ENROLLMENT_LIMIT = 100000;


    public int getEnrollmentLimit() { return this.enrollmentLimit; }

    public void setEnrollmentLimit(int enrollLimit) {
        if (enrollLimit == students.size()) {
            this.enrollmentLimit = enrollLimit;
        } else if (enrollLimit > students.size()) {
            int space = enrollLimit - students.size();
            this.enrollmentLimit = enrollLimit;
            enrollInAutomate(space);
        } else if (enrollLimit < students.size()){
           throw new IllegalArgumentException();
        }
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

    public Set<Student> getStudents() { return Collections.unmodifiableSet(students); }

    public List<Student> getWaitList() { return Collections.unmodifiableList((List<? extends Student>) waitList); }

    public void enroll(Student student) {
            students.add(student);
    }

    public void addToWaitList(Student student) { waitList.add(student); }

    public void drop(Student student) {
        if (students.contains(student)) {
            if (!(waitList.isEmpty())) {
                students.remove(student);
                Student newStudent = waitList.remove();
                newStudent.enrollIn(this);
            } else { students.remove(student);
            }
        } else if (waitList.contains(student)) {
            waitList.remove(student);
        }
    }

    private void enrollInAutomate(int space) {
        if (!(waitList.isEmpty())) {
            if (waitList.size() > space) {
                for (int i = 0; i < space; i++) {
                    Student newStudent = waitList.remove();
                    newStudent.enrollIn(this);
                }
            } else {
                int waitListSize = waitList.size();
                for (int i =0; i < waitListSize; i++) {
                    Student newStudent = waitList.remove();
                    newStudent.enrollIn(this);
                }
            }
        }
    }
}
