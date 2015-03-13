package edu.macalester.registrar;

import java.util.*;
import java.util.NoSuchElementException;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private Queue<Student> waitList = new LinkedList<Student>();
    private int enrollmentLimit;


    public Queue<Student> getWaitList() {
        return waitList;
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

    public int getEnrollmentLimit() {
        return enrollmentLimit;
    }

    public void setEnrollmentLimit(int enrollmentLimit) {
        this.enrollmentLimit = enrollmentLimit;
    }

    void enroll(Student student) {
        students.add(student);
    }

    void addToWaitList(Student student){
        waitList.add(student);
    }
    void updateWaitList (){
        try{
            waitList.remove().enrollIn(this);
        } catch (NoSuchElementException error){
            System.out.println("Uh oh. This student was not on the waitlist or the waitlist is empty.");
        }
    }

    void dropStudent(Student student){
        students.remove(student);
    }
}
