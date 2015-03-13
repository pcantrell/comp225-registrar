package edu.macalester.registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private int maxEnrollment;
    private Set<Student> waitList = new HashSet<Student>();
    private LinkedList<Student> waitListOrder = new LinkedList<Student>();

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

    public Set<Student> getWaitListStudents() {
        return Collections.unmodifiableSet(waitList);
    }

    void enroll(Student student) {
        if (!this.isFull() && !this.students.contains(student)){
            this.students.add(student);
        }
        else if (this.isFull() && !this.students.contains(student) && !this.waitList.contains(student)){
            this.waitListOrder.push(student);
            this.waitList.add(student);
        }
    }

    void drop(Student student) {
        if (this.students.contains(student) && this.waitList.size()==0){
            students.remove(student);
        }
        else if (this.students.contains(student) && this.waitList.size()>0){
            this.students.remove(student);
            Student s = this.waitListOrder.removeLast();
            this.waitList.remove(s);
            s.enrollIn(this);
        }
        else {
            if (this.waitList.contains(student)){
                this.waitList.remove(student);
                this.waitListOrder.remove(student);
            }
        }
    }

    public int getEnrollmentLimit(){
        return this.maxEnrollment;
    }

    public void setMaxEnrollment(int max){
        this.maxEnrollment = max;
    }

    public boolean isFull(){
        if (this.maxEnrollment > this.students.size()){
            return false;
        }
        else return true;
    }


}
