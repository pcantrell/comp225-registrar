package edu.macalester.registrar;
import groovy.ui.SystemOutputInterceptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class Course {

    public static final int NO_ENROLLMENT_LIMIT = 2147483647;

    private String catalogNumber, title;

    private Set<Student> students = new HashSet<Student>();

    private List<Student> waitList = new ArrayList<Student>();

    private int enrollmentLimit = NO_ENROLLMENT_LIMIT;

    public int getEnrollmentLimit() {
        return enrollmentLimit;
    }

    public void setEnrollmentLimit(int enrollmentLimit) {
        if (enrollmentLimit < this.getStudents().size()) {
            throw new IllegalArgumentException("You cannot set enrollment limit to a number lower than your current class size.");
        } else {
            this.enrollmentLimit = enrollmentLimit;
            while (!this.getWaitList().isEmpty() && enrollmentLimit <= this.getStudents().size()) {
                getWaitList().get(0).enrollIn(this);
            }
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

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    public List<Student> getWaitList() {
        return Collections.unmodifiableList(waitList);
    }

    void enroll(Student student) {
        if (this.getStudents().size() < this.getEnrollmentLimit()) { //if there is room in the course
            students.add(student); //course adds student to students
            if (waitList.contains(student)){ //if the student was on the wait list, they are removed
                waitList.remove(student);
            }
        } else {
            waitList.add(student);
        }
    }

    void drop(Student student) {
        if (this.getWaitList().contains(student)) {
            waitList.remove(student);
        } else {
            students.remove(student);
            if (!waitList.isEmpty()) {
                Student firstOnWaitList = waitList.get(0);
                waitList.remove(firstOnWaitList);
                firstOnWaitList.enrollIn(this);
                System.out.println(student.getName() + " has dropped '" + this.getTitle() + ".' " + firstOnWaitList.getName() + " was first on the wait list, so " + firstOnWaitList.getName() + " has now been enrolled. ");
                for (Student stud : this.getWaitList()) {
                    System.out.println(stud.getName());
                }
            }
        }
    }
}