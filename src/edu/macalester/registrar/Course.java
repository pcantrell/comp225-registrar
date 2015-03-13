package edu.macalester.registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private LinkedList<Student> waitList = new LinkedList<Student>();
    private boolean courseFull;
    private int enrollLim;

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    void addToWait(Student student) {
        this.waitList.add(student);
    }

    public LinkedList<Student> getWaitList() {return waitList; }


    void enroll(Student student) {

        System.out.println("does enroll run");

        if (this.getStudents().size() < this.getEnrollLimit()) {
            this.courseFull = false;
            students.add(student);

            if (this.getStudents().size() == this.getEnrollLimit()) {
                this.courseFull = true;
                System.err.println("Maximum Course Limited Reached: " + this.getEnrollLimit());

            }

            else if (this.getStudents().size() > this.getEnrollLimit()) {
                this.courseFull = true;
                this.addToWait(student);
                System.err.println("Maximum Course Limited Reached: " + this.getEnrollLimit());

            }

        }

        else {

            System.out.println("Are you there?");
            this.addToWait(student);
            System.err.println("Maximum Course Limited Reached: " + this.getEnrollLimit());

        }

    }

    public void setEnrollLimit(int newLim) {this.enrollLim = newLim; }

    public int getEnrollLimit() {return enrollLim; }

    public boolean getCourseStatus() {return courseFull; }

}
