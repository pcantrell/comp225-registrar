package edu.macalester.registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private Integer enrollmentLimit;
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

    public void setEnrollmentLimit(Integer number) {this.enrollmentLimit = number;}

    public void resetEnrollmentLimit(){this.enrollmentLimit = 0;}

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    void enroll(Student student) {
        students.add(student);
    }

    void drop (Student student){
        students.remove(student);
    }

    void addWaitList(Student student){
        waitList.add(student);
    }

    void removeWaitList(Student student){
        waitList.remove(student);
    }

    void replace(){
        waitList.get(0).enrollIn(this);
    }

    public Integer getEnrollmentLimit() {
        if (enrollmentLimit == null){
        }
        return enrollmentLimit;
    }

}

