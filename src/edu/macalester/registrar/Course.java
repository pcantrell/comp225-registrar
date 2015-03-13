package edu.macalester.registrar;

import java.util.*;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private int enrollLimit = Integer.MAX_VALUE; // Can enroll essentially unlimited number of students unless stated
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

    public int getEnrollLimit() {return enrollLimit;}

    public void setEnrollLimit(int newLimit) {
        //TODO  modify this to change enrollment / wait lists for e.c.
        this.enrollLimit = newLimit;
        // if new enrollment > old : remove students from waitlist, add to course
        // else if new enrollment < old : return fals, don't allow to lower limit
        //if equal, do nothing.
    }
    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    public List<Student> getWaitList() {
        return Collections.unmodifiableList(waitList);
    }

    Boolean enroll(Student student) {
        if (this.getStudents().size() >= this.getEnrollLimit()){
            System.out.println(student.getName() + " was not enrolled. This operation would exceed the enrollment limit.");
            this.waitList.add(student);
            System.out.println(student.getName() + " was added to the waitlist.");
            return false;
        }
        else {
            students.add(student);
            return true;
        }
    }

    void drop(Student student) {
        this.students.remove(student);
        if (this.waitList.size() > 0){
            Student s = this.waitList.remove(0);
            s.enrollIn(this);

        }
    }
}
