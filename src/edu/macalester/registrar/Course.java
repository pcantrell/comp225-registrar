package edu.macalester.registrar;

import java.util.*;
import java.util.Collections;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private ArrayList<Student> waitList = new ArrayList<Student>();
    public static int NO_ENROLLMENT_LIMIT = Integer.MAX_VALUE;
    int enrollmentLimit = NO_ENROLLMENT_LIMIT;

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
        students.add(student);
    }

    public int getEnrollmentLimit() {
        return enrollmentLimit;
    }

    public void setEnrollmentLimit(int newLimit) {
        if(newLimit >= students.size()){
            enrollmentLimit = newLimit;
//            while(students.size()<=enrollmentLimit){
//                transferFromWaitlist();
//            }
        }else if (newLimit < this.getStudents().size()){
            throw new IllegalArgumentException("Enrollment Limit cannot be less than the number of students already enrolled.");
        }else if (newLimit == NO_ENROLLMENT_LIMIT){
            enrollmentLimit = newLimit;
//            while (!waitList.isEmpty()){
//                transferFromWaitlist();
//            }

        }
    }

    private void transferFromWaitlist() {
        Student student = waitList.remove(0);
        student.enrollIn(this);
    }

    public Collection<Student> getWaitList() {
        if (waitList.isEmpty()){
            return Collections.emptyList();
        } else {
            return Collections.unmodifiableList(waitList);
        }
    }

    public void addToWaitList(Student student){
        waitList.add(student);
    }

    public void removeFromWaitList(Student student){
        waitList.remove(student);
    }

    public void dropStudent(Student student) {
        students.remove(student);
        if (!waitList.isEmpty()) {
            student = waitList.get(0);
            waitList.remove(0);
            student.enrollIn(this);
        }
    }


}
