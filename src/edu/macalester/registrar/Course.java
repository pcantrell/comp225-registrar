package edu.macalester.registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class Course {

    private String catalogNumber, title;

    public static int NO_ENROLLMENT_LIMIT = 9999999;
    public int ENROLLMENT_LIMIT = NO_ENROLLMENT_LIMIT;
    private Set<Student> students = new HashSet<Student>();
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
        return Collections.unmodifiableList(this.waitList);
    }

    public boolean enroll(Student student) {
        if (!this.isFull() || this.students.contains(student)){
            if (!this.students.contains(student)) {
                this.students.add(student);
            }
            return true;
        }
        else {
            if (!this.waitList.contains(student)){
                this.waitList.add(student);
            }
            return false;
        }
    }

    void drop(Student student) {
        if (this.students.contains(student)){
            this.students.remove(student);
            if (this.waitList.size()>0) {
                Student s = waitList.remove(0);
                s.enrollIn(this);
            }
        }
        else {
            if (this.waitList.contains(student)){
                this.waitList.remove(student);
            }
        }
    }

    public int getEnrollmentLimit(){
        return this.ENROLLMENT_LIMIT;
    }

    public void setEnrollmentLimit(int max){
        if (this.students.size() <= max || (this.students.size() > max && this.ENROLLMENT_LIMIT<max)) {
            this.ENROLLMENT_LIMIT = max;
            while (!this.isFull() && waitList.size() > 0) {
                Student s = waitList.remove(0);
                s.enrollIn(this);
            }
        }
    }

    public boolean isFull(){
        if (this.ENROLLMENT_LIMIT > this.students.size()){
            return false;
        }
        else return true;
    }


}
