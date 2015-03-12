package edu.macalester.registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Queue;
import java.util.LinkedList;
import java.util.NoSuchElementException;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private Queue<Student> waitList = new LinkedList<Student>();
    private int courseLimit;

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

    public void setCourseLimit(int i) {
        this.courseLimit = i;
    }

    public void addToWaitList(Student s) {
        waitList.add(s);
    }

    public void resolveWaitlist() {
        if (!this.isFull()) {
            try {
                waitList.remove().enrollIn(this);
            } catch (NoSuchElementException e) {
                System.out.println("No students in wait list to add.");
            }
        }
    }

    public boolean isFull() {
        return students.size() == courseLimit;
    }

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    public void dropStudent(Student s) {
        students.remove(s);
    }

    public void enroll(Student student) {
        students.add(student);
    }
}
