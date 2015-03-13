package edu.macalester.registrar;

import java.util.*;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private int enrollmentLimit = Integer.MAX_VALUE;
    private Queue<Student> waitList = new LinkedList();

    public boolean isFull() { return (students.size() >= getEnrollmentLimit()); }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public int getEnrollmentLimit() { return enrollmentLimit; }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public void setEnrollmentLimit(int enrollmentLimit) { this.enrollmentLimit = enrollmentLimit; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    public Queue<Student> getWaitList() {
        return waitList;
    }

    void liftEnrollmentLimit() {
        if (enrollmentLimit != 0 && enrollmentLimit != Integer.MAX_VALUE) {
            // lift enrollment limit
            setEnrollmentLimit(Integer.MAX_VALUE);
        }
    }

    void enroll(Student student, boolean enrollmentPossible) {
        if (enrollmentPossible) {
            if (students.contains(student)) {
                System.out.println("I'm sorry, " + student.getName() + " cannot be enrolled twice!");
            } else if (!students.contains(student)) {
                students.add(student);
            }
        } else if (!enrollmentPossible) {
            if (students.contains(student)) {
                System.out.println("I'm sorry, " + student.getName() + " cannot be enrolled twice!");
            } else if (!students.contains(student)) {
                addToWaitList(student);
            }
        }
    }

    void addToWaitList(Student student) {
        if (!waitList.contains(student)) {
            waitList.add(student);
            System.out.println(waitList.element().getName() + " is wait-listed!");
        }
    }

    void drop(Student student) {
        if (!students.isEmpty() && (students.contains(student))) {
            students.remove(student);
            enrollWaitList();
        }
    }

    /*
    *   If a course is not full, then keep enrolling wait-listed students
    */
    void enrollWaitList() {
        while (!waitList.isEmpty() && (students.size() < getEnrollmentLimit())) {
            Student firstInLine = waitList.poll();
            students.add(firstInLine);
        }
    }
}
