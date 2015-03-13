
//Clare Speer 
//March 13, 2015

package edu.macalester.registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.LinkedList;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private int enrollmentLimit;
    private List<Student> waitlist = new LinkedList<Student>();

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
    
    public void setEnrollmentLimit(int enrollmentLimit){
        this.enrollmentLimit = enrollmentLimit;
    }
    
    public int getEnrollmentLimit(){
        return enrollmentLimit;
    }
    
    public List<Student> getWaitlist(){
        return waitlist;
    }
    
    public void addToWaitlist(Student student){
        waitlist.add(student);
    }

    void enroll(Student student) {
        if(getStudents().size() == getEnrollmentLimit()){
            System.out.println("Enrollment limit met in " + getTitle() + ". Student cannot be enrolled in the class.");
        }
        else{
            students.add(student);
        }
    }
    
    void drop(Student student){
        students.remove(student);
    }
    
}
