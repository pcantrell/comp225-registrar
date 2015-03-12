package edu.macalester.registrar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private ArrayList<Student> waitList = new ArrayList<Student>();
    private int enrollmentLimit;

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

    public ArrayList<Student> getWaitList() {return waitList; }

    public int getEnrollmentLimit() { return enrollmentLimit; }

    public void setEnrollmentLimit(int enrollmentLimit) { this.enrollmentLimit = enrollmentLimit; }

    public void enroll(Student student) {
        if (this.students.size() >= this.enrollmentLimit) {
            System.out.println("Enrollment limit reached. Added to Wait List");
            waitList.add(student);
        }

        else {
            students.add(student);
            System.out.println(student.getName() + " enrolled in " + this.getTitle());
        }

    }


    public void waitListToStudents(){
        if (this.students.size() >= this.enrollmentLimit) {
            System.out.println("Enrollment Limit Reached.");
        }
        else {
            students.add(waitList.get(0));
        }
    }


}
