package edu.macalester.registrar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private Integer enrollmentLimit = 0;
    private ArrayList<Student> waitList = new ArrayList<>(); //An arraylist so you can add as many students into the wait list, and easily grab student at position 0

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

    public ArrayList<Student> getWaitList() { return waitList; }

    public int getEnrollmentLimit() { return enrollmentLimit; }

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    void enroll(Student student) {
        if (getRemainingSeats() != 0){
            students.add(student);
            System.out.println(getTitle()+" has accepted and enrolled "+student.getName()+".");
        }
        else{
            System.out.println(student.getName() + " cannot enroll in " + getTitle() + " because it's full.");
            System.out.println();
        }
    }

    void removeStudent(Student student){
        students.remove(student);
        if (!(waitList.isEmpty())){
            Student newStud = waitList.get(0);
            newStud.enrollIn(this);
            waitList.remove(0);
        }
    }

    int getRemainingSeats(){
        if (students.size() < enrollmentLimit){
            return getEnrollmentLimit() - getStudents().size();
        }
        else{
            return 0;
        }
    }

    void setEnrollmentLimit(int number){
        if (number != 0){
            enrollmentLimit = number;
        }
        else{
            enrollmentLimit = null;
        }
    }
}