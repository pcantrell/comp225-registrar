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

    /**
    *   This method lifts the enrollment limit to the max integer value, once the enrollment limit is set
    *   After lifting the limit, admit all wait-listed students
    */
    void liftEnrollmentLimit() {
        if (enrollmentLimit != 0 && enrollmentLimit != Integer.MAX_VALUE) {
            // lift enrollment limit
            setEnrollmentLimit(Integer.MAX_VALUE);
            // now put all current wait-listed student in the class
            enrollWaitList();
        }
    }

    /**
    *   This method takes in two arguments, the student and a boolean value depending on whether course is full
    *   @param student  the student caller is asking to enroll in the course
    *   @param enrollmentPossible   boolean value determining if course is already full
    */
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

    /**
    *   This method takes in the student as argument, then check if the waitlist already contains her, then add her to
    *   the list and inform the caller of the wait-list status
    *   @param student  the student caller is asking to enroll in the course parsed from enroll()
    */
    void addToWaitList(Student student) {
        if (!waitList.contains(student)) {
            waitList.add(student);
            System.out.println(student.getName() + " is wait-listed for " + this.getCatalogNumber() + ": " + this.getTitle());
        }
    }

    /**
     *   This method takes in the student as argument, then drop her from the class. the waitlisted students are then
     *   admitted
     *   @param student  the student caller is asking to drop the class
     */
    void drop(Student student) {
        if (!students.isEmpty() && (students.contains(student))) {
            students.remove(student);
            enrollWaitList();
        }
    }

    /**
    *   If a course is not full, then keep enrolling wait-listed students
    */
    void enrollWaitList() {
        while (!waitList.isEmpty() && (!isFull())) {
            Student firstInLine = waitList.poll();
            firstInLine.enrollIn(this);
        }
    }
}
