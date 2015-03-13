package edu.macalester.registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private Set<Student> waitlist = new HashSet<Student>();
    private int enrollmentLimit = 1;

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

    public Set<Student> getWaitlist() {
        return Collections.unmodifiableSet(waitlist);
    }

    void enroll(Student student) {
        if(enrollmentLimit()) { students.add(student);}
        else if(!students.contains(student)){ addToWaitlist(student);}
    }

    void drop(Student student){
        students.remove(student);
        moveFromWaitlist();
    }

    void addToWaitlist(Student student){
        waitlist.add(student);
    }

    public void moveFromWaitlist(){
        if(waitlist.size() > 0){
            students.add(waitlist.iterator().next());
            waitlist.remove(waitlist.iterator().next());
        }
    }

    //Checks to see if the number of students has not yet reached the enrollment limit
    //returns True if max not yet reached, false if it has
    boolean enrollmentLimit(){
        if(this.getStudents().size() < this.enrollmentLimit){return true;}
        else{return false;}
    }

}
