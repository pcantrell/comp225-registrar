package edu.macalester.registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.LinkedList;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private int enrollmentLimit = Integer.MAX_VALUE;
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
        this.enrollmentLimit = enrollmentLimit;
    }

    public int getEnrollmentLimit() {
        return this.enrollmentLimit;
    }

    void enroll(Student student) {
        if (this.getStudents().size() >= this.getEnrollmentLimit()) {
            if (!this.waitList.contains(student)) {
                System.out.println("Attempt to over-enroll. " + student.getName() + " added to wait list.");
                this.waitList.add(student);
            }
        } else {
            students.add(student);
        }
    }

    void drop(Student student) {
        this.students.remove(student);
        this.waitList.remove(student);
        if ((this.waitList.size() > 0) && (this.getStudents().size() < this.getEnrollmentLimit())) {
            students.add(this.waitList.get(0));
            this.waitList.remove(0);
        }
    }
}
